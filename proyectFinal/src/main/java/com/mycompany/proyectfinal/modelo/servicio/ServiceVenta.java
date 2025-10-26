package com.mycompany.proyectfinal.modelo.servicio;

import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.Medicamento;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase de servicio que contiene la lógica de negocio relacionada con ventas.
 * Centraliza las reglas de negocio como cálculo de impuestos, descuentos,
 * validaciones y aplicación de políticas comerciales.
 * Esta capa separa la lógica de negocio del acceso a datos (DAOs) y 
 * de la presentación (Controladores).
 * 
 * @author Gomez Marco, Courel Brian, Lairana Rocio
 */

public class ServiceVenta {
    
    private static final BigDecimal IVA_DEFAULT = new BigDecimal("0.21");
    private static final BigDecimal MONTO_MINIMO_DESCUENTO = new BigDecimal("10000");
    private static final BigDecimal DESCUENTO_DEFAULT = new BigDecimal("0.10");
    /**Escala decimal para monetarios*/
    private static final int ESCALA_MONETARIA = 2;
    /**Modo de redondeo para operaciones monetarias*/
    private static final RoundingMode MODO_REDONDEO = RoundingMode.HALF_UP;
    
    // === Métodos de cálculos de Detalles. ===
    
    /**
     * Método que alcula todos los valores de un detalle de venta aplicando impuestos y descuentos.
     * Aplica las reglas de negocio definidas para el sistema.
     * 
     * Proceso:
     * 1. Calcula el subtotal (precio unitario × cantidad)
     * 2. Aplica el impuesto sobre el subtotal
     * 3. Aplica descuento si el subtotal supera el monto mínimo
     * 4. Calcula el precio final (subtotal + impuesto - descuento)
     * 
     * @param detalle Detalle de venta a calcular
     * @param pI Porcentaje de impuesto (ejemplo: 0.21 para 21%)
     * @param mMD Monto mínimo de subtotal para aplicar descuento
     * @param pD Porcentaje de descuento (ejemplo: 0.10 para 10%)
     * @return El mismo detalle con todos los cálculos aplicados
     * @throws IllegalArgumentException Si los parámetros son negativos o nulos
     */
    
    public DetalleVenta calcularTotalDetalles(DetalleVenta detalle, BigDecimal pI, BigDecimal mMD, BigDecimal pD){
         validarParametrosCalculos(pI, mMD, pD);
         
         if(detalle == null){
             throw new IllegalArgumentException("¡ATENCION! El detalle no puede ser nulo.");
         }
         
         detalle.calcularSubtotal();
         BigDecimal subtotal = detalle.getSubtotal();
         
         BigDecimal impuesto = calcularImpuesto(subtotal, pI);
         detalle.setImpuesto(impuesto);
         
         BigDecimal descuento = calcularDescuento(subtotal, mMD, pD);
         detalle.setDescuento(descuento);
         
         detalle.calcularPrecioFinal();
         
         return detalle;
    }
    
    /**
     * Método que calcula los totales de un detalle usando los valores por defecto del sistema.
     * 
     * @param detalle de Venta a calcular
     * @return El detalle con los cáculos aplicados usando los valores predeterminados.
     */
    
    public DetalleVenta calculoTotalDetallesDefualt(DetalleVenta detalle){
        return calcularTotalDetalles(detalle, IVA_DEFAULT,
                MONTO_MINIMO_DESCUENTO, DESCUENTO_DEFAULT);
    }
    
    /**
     * Método que calcula los totales de un detalle con los porcentajes personalizados.El mono minimo para descuento usa el valor predeterminado del sistema.
     *
     * @param detalle Detalle a calcular
     * @param pI Porcentaje de Impuesto personalizado
     * @param pD Porcentaje de Descuento personalizado
     * @return  El detalle calculado
     */
    
    public DetalleVenta calcularTotalesPersonalizados(DetalleVenta detalle, BigDecimal pI, BigDecimal pD){
        return calcularTotalDetalles(detalle, pI, MONTO_MINIMO_DESCUENTO, pD);
    }
    
    // === Métodos de cálculos de Ventas. === 
    
    /**
     * Método que calcula los totales de una venta completa basándose en sus detalles
     * Suma todos los subtotales, impuestos, descuentos y calcula el total final.
     * @param venta Venta a calcular
     * @return La venta con totales actualizados
     * @throws IllegalArgumentException si la venta es nula
     */
    
    public Venta calcularTotalesDeVenta(Venta venta){
        
        if(venta == null){
            throw new IllegalArgumentException("¡ATENCION! La venta no puede ser nula.");
        }
        
        venta.recalcularTotales();
        
        return venta;
    }
    
    /**
     * Método que prepara una venta completa con sus respectivos cálculos y detalles.este método nos es útil antes de guardar una venta en la base de datos.
     * @param venta Venta a preparar
     * @param pI Porcentaje de Impuesto a aplicar
     * @param pD Porcentaje de Descuento
     * @return La Venta final completamente calculada.
     */
    
    public Venta prepararVenta(Venta venta, BigDecimal pI, BigDecimal pD){
        if(venta == null){
            throw new IllegalArgumentException("¡ATENCION! La venta no puede ser nula.");
        }
        
        for(DetalleVenta detalle : venta.getDetalles()){
            calcularTotalesPersonalizados(detalle, pI, pD);
        }
        
        venta.recalcularTotales();
        
        return venta;
    }
    
    // === Métodos de Cálculos Auxiliares. ===
    
    /**
     * Método que calcula el impuesto de un monto dado.
     * @param monto Monto base sobre el cual se va a calcular el impuesto
     * @param pI Porcentaje de impuesto predefinido (0.21 = 21%) 
     * @return Monto del impuesto redondeado a 2 decimales.
     */
    
    public BigDecimal calcularImpuesto(BigDecimal monto, BigDecimal pI){
     
        if(monto == null || pI == null){
            return BigDecimal.ZERO;
        }
        
        return monto.multiply(pI)
                .setScale(ESCALA_MONETARIA, MODO_REDONDEO);
    }
    
    /**
     * Método que calcula el descuento a aplicar basándose en el subtotal.
     * Aplicándose solo si el monto de la compra supera el minimo establecido.
     * @param st Subtotal
     * @param mM Monto minimo
     * @param pD Porcentaje de descuento
     * @return Monto del descuento, deja en cero si no alcanza el mínimo.
     */
    
    public BigDecimal calcularDescuento(BigDecimal st, BigDecimal mM, BigDecimal pD){
        
        if(st == null || mM == null || pD == null){
            return BigDecimal.ZERO;
        }
        
        if(st.compareTo(mM) >= 0){
            return st.multiply(pD)
                    .setScale(ESCALA_MONETARIA, MODO_REDONDEO);
        }
        
        return BigDecimal.ZERO;
    }
    
    /**
     * Método que calcula el total de una compra (subtotal + impuesto - descuento).
     * @param subtotal
     * @param impuesto
     * @param descuento
     * @return Total final redondeado a 2 decimales.
     */
    
    public BigDecimal calcularTotal(BigDecimal subtotal, BigDecimal impuesto, BigDecimal descuento){
        
        BigDecimal sub = (subtotal != null) ? subtotal : BigDecimal.ZERO;
        BigDecimal imp = (impuesto != null) ? impuesto : BigDecimal.ZERO;
        BigDecimal desc = (descuento != null) ? descuento : BigDecimal.ZERO;
        
        return sub.add(imp).subtract(desc)
                .setScale(ESCALA_MONETARIA, MODO_REDONDEO);
    }
    
    // === Métodos de Validación. === 
    
    /**
     * Método que valida un detalle de venta antes de procesarlo.
     * Verifica datos como: cantidad positiva, stock disponible y precio válido.
     *
     * @param detalle Detalle a validar.
     * @param medicamento Medicamento asociado al detalle
     * @return  true solo si el detalle es válido.
     */
    
    public boolean validarDetalles(DetalleVenta detalle, Medicamento medicamento){
        
        if(detalle == null || medicamento == null){
            return false;
        }
        
        if(detalle.getCantidad() <= 0){
            return false;
        }
        
        if(medicamento.getStock() < detalle.getCantidad()){
            return false;
        }
        
        if(detalle.getPrecioUnitario() == null || 
           detalle.getPrecioUnitario().compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }
        
        return true;
    }
    
    /**
     * Método que valida que la venta contenga detalles.
     * @param venta Venta a validar
     * @return true solo si la venta es válida.
     */
    
    public boolean validarVenta(Venta venta){
        
        if(venta.getDetalles() == null || venta.getDetalles().isEmpty()){
            return false;
        }
        
        if(venta.getClienteId() <= 0){
            return false;
        }
        
        if(venta.getTotalFinal() == null ||
           venta.getTotalFinal().compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }
        
        return true;
    }
    
    /**
     * Método que valida los parámetros de cálculo.
     * 
     * @param pI Porcentaje de impuesto
     * @param mM Monto mínimo para descuento
     * @param pD Porcentaje de descuento
     * @throws IllegalArgumentException Si algún parámetro es inválido
     */
    
    private void validarParametrosCalculos(BigDecimal pI, BigDecimal mM, BigDecimal pD){
        if(pI == null || pI.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("¡ATENCION! El Porcentaje de Impuesto debe ser mayor o igual a cero.");
        }
        if(mM == null || mM.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("¡ATENCION! El Monto Minimo debe ser mayor o igual a cero.");
        }
        if(pD == null || pD.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("¡ATENCION! El Porcentaje de Descuento debe ser mayor o igual a cero.");
        }
        if(pI.compareTo(BigDecimal.ONE) > 0){
            throw new IllegalArgumentException("¡ATENCION! El Porcentaje de Impuesto no debe ser mayor a 100%.");
        }
        if(pD.compareTo(BigDecimal.ONE) > 0){
            throw new IllegalArgumentException("¡ATENCION! El Porcentaje de Descuento no debe ser mayor a 100%.");
        }
    }
    
    // === Métodos de cálculos de Negocios Específicos. === 
    
    /**
     * Método que valida si una compra califica para el descuento
     * 
     * @param st subtotal a tener en cuenta.
     * @return true solo si califica para el descuento.
     */
    
    public boolean calificaParaDescuento(BigDecimal st){
        
        if(st == null){
            return false;
        }
        
        return st.compareTo(MONTO_MINIMO_DESCUENTO) >= 0;
    }
    
    /**
     * Método que aplica un descuento a un detalle de venta.
     * 
     * @param detalle Al que aplica el descuento.
     * @param descEsp Descuento Especial que aplica
     * @return  El detalle con el descuento aplicado.
     */
    
    public DetalleVenta aplicarDescuento(DetalleVenta detalle, BigDecimal descEsp){
        
        if(detalle == null || descEsp == null){
            throw new IllegalArgumentException("¡ATENCION! Parámetros inválidos.");
        }
        
        if(descEsp.compareTo(BigDecimal.ZERO) < 0){
             throw new IllegalArgumentException("¡ATENCION! El Descuento no puede ser negativo.");
        }
        
        if(descEsp.compareTo(detalle.getSubtotal()) > 0){
            throw new IllegalArgumentException("¡ATENCION! El descuento no puede ser mayor al subtotal.");
        }
        
        detalle.setDescuento(descEsp);
        detalle.calcularPrecioFinal();
        
        return detalle;
    }
    
    /**
     * Método que obtiene y muestra un resumen de venta antes de confirmar la operacion
     * @return 
     */
    
    public String obtenerResumenVenta(Venta venta){
        
        if(venta == null){
            return "Venta no disponible.";
        }
        
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== RESUMEN DE VENTA ===");
        resumen.append("------------------------");
        resumen.append("Cantidad de Items: ").append(venta.getDetalles().size()).append("\n");
        resumen.append("Subtotal: $").append(venta.getSubtotalBruto()).append("\n");
        resumen.append("Impuesto: $").append(venta.getImpuestoTotal()).append("\n");
        resumen.append("Descuento: $").append(venta.getDescuentoTotal()).append("\n");
        resumen.append("TOTAL FINAL: $").append(venta.getTotalFinal()).append("\n");
        resumen.append("------------------------");
        
        return resumen.toString();
    }
    
    // Se crean los Getters y Setters
    
    public BigDecimal getIvaDefault(){
        return IVA_DEFAULT;
    }
    
    public BigDecimal getMontoMinimoDescuento(){
        return MONTO_MINIMO_DESCUENTO;
    }
    
    public BigDecimal getDescuentoDefault(){
        return DESCUENTO_DEFAULT;
    }
}
