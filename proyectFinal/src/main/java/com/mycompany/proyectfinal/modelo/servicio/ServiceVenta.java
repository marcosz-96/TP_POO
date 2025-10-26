package com.mycompany.proyectfinal.modelo.servicio;

import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.Medicamento;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
     * Método que calcula la suma de los totales mas los impuestos
     * sin aplicar descuento.
     * El descuento se aplica al momento de confirmar la venta.
     * @param dv Detalles a calcular
     * @param porcImp Porcentaje de impuesto, por defecto 21%
     * @return 
     */
    
    public DetalleVenta calcularDetalleConImpuesto(DetalleVenta dv, BigDecimal porcImp){
        if(dv == null){
            throw new IllegalArgumentException("¡ATENCION! El detalle de venta no puede ser nulo.");
        }
        
        dv.calcularSubtotal();
        BigDecimal st = dv.getSubtotal();
        
        BigDecimal imp = st.multiply(porcImp)
                .setScale(ESCALA_MONETARIA, MODO_REDONDEO);
        
        dv.setImpuesto(imp);
        
        dv.calcularPrecioFinal();
        
        return dv;
    }
    
    /**
     * Método que calcula los totales de un detalle usando valor del IVA por defecto del sistema.
     * 
     * @param detalle de Venta a calcular
     * @return El detalle con los cáculos aplicados usando los valores predeterminados.
     */
    
    public DetalleVenta calculoTotalDetallesDefualt(DetalleVenta detalle){
        return calcularDetalleConImpuesto(detalle, IVA_DEFAULT);
    }
    
    /**
     * Método que aplica el descuento usando el valor por defecto del sistema.
     * El descuento se aplica solamente si supera el minimo requerido.
     * Distribuyendose proporcionalmente segun el subtotal de cada detalle.
     * 
     * @param detalles
     * @param pD
     * @return 
     */
    
    public List<DetalleVenta> aplicarDescuentoSiCorresponde(List<DetalleVenta> detalles, BigDecimal pD){
        
        if(detalles == null || detalles.isEmpty()){
            return detalles;
        }
        
        BigDecimal subtotalTotal = BigDecimal.ZERO;
        for(DetalleVenta detalle : detalles){
            subtotalTotal = subtotalTotal.add(detalle.getSubtotal());
        }
        
        boolean aplicaDescuento = subtotalTotal.compareTo(MONTO_MINIMO_DESCUENTO) >= 0;
        
        if(!aplicaDescuento){
            for(DetalleVenta detalle : detalles){
                detalle.setDescuento(BigDecimal.ZERO);
                detalle.calcularPrecioFinal();
            }
            return detalles;
        }
        
        BigDecimal descuentoTotal = subtotalTotal.multiply(pD)
                .setScale(ESCALA_MONETARIA, MODO_REDONDEO);
        
        BigDecimal descuentoAcumulado = BigDecimal.ZERO;
        
        for(int i = 0; i < detalles.size(); i++){
            DetalleVenta detalle = detalles.get(i);
            BigDecimal descuentoEnDetalles;
            
            if(i == detalles.size() -1){
                descuentoEnDetalles = descuentoTotal.subtract(descuentoAcumulado);
            }else{
                BigDecimal proporcion = detalle.getSubtotal()
                        .divide(subtotalTotal, 2, RoundingMode.HALF_UP);
                descuentoEnDetalles = descuentoTotal.multiply(proporcion)
                        .setScale(ESCALA_MONETARIA, MODO_REDONDEO);
                descuentoAcumulado = descuentoAcumulado.add(descuentoEnDetalles);
            }
            
            detalle.setDescuento(descuentoEnDetalles);
            detalle.calcularPrecioFinal();
        }
        
        return detalles;
    }
    
    /**
     * Método que aplica los descuentos utilizando los valores por defecto del sistema
     * @param detalles
     * @return 
     */
    
    public List<DetalleVenta> aplicarDescuentoDefault(List<DetalleVenta> detalles){
        return aplicarDescuentoSiCorresponde(detalles, DESCUENTO_DEFAULT);
    }
    
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
     * Método que calcula subtotal final de una lista de detalles
     * @param dv
     * @return 
     */
    
    public BigDecimal calcularSubtotalFinal(List<DetalleVenta> dv){
        BigDecimal subtotalFinal = BigDecimal.ZERO;
        if(dv != null){
            for(DetalleVenta dvs : dv){
                subtotalFinal = subtotalFinal.add(dvs.getSubtotal());
            }
        }
        
        return subtotalFinal;
    }
    
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
        
        if(venta == null){
            return false;
        }
        
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
     * Método que calcula los totales de una venta basándose en sus detalles
     * @param venta
     * @return 
     */
    
    public Venta calcularTotalFinal(Venta venta){
        if(venta == null){
            throw new IllegalArgumentException("¡ATENCION! La venta no puede ser nula.");
        }
        
        venta.recalcularTotales();
        return venta;
    }
    
    /**
     * Método que obtiene y muestra un resumen de venta antes de confirmar la operacion
     * @param venta
     * @return 
     */
    
    public String obtenerResumenVenta(Venta venta){
        
        if(venta == null){
            return "Venta no disponible.";
        }
        
        StringBuilder resumen = new StringBuilder();
        resumen.append("=== RESUMEN DE VENTA ===\n");
        resumen.append("------------------------\n");
        resumen.append("Cantidad de Items: ").append(venta.getDetalles().size()).append("\n");
        resumen.append("Subtotal: $").append(venta.getSubtotalBruto()).append("\n");
        resumen.append("Impuesto: $").append(venta.getImpuestoTotal()).append("\n");
        resumen.append("Descuento: $").append(venta.getDescuentoTotal()).append("\n");
        resumen.append("------------------------\n");
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
