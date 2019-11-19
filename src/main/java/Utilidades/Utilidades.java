package Utilidades;

import Objetos.Vacacion;
import com.sun.prism.impl.Disposer.Record;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose_
 */

public class Utilidades {

    public static boolean obtenerBooleano(int a){
        if(a==1){
            return true;
        }else{
            return false;
        }
    }
    
    public static LocalDate sumarAnio(LocalDate date, int cantidad){
        LocalDate dateFinal = null;
        dateFinal = date.plusYears(cantidad);
        return dateFinal;
    }
    
    public static LocalDate sumarMes(LocalDate date, int cantidad){
        LocalDate dateFinal = null;
        dateFinal = date.plusMonths(cantidad);
        return dateFinal;
    }
    
    public static LocalDate sumarDia(LocalDate date, int cantidad){
        LocalDate dateFinal= null;
        dateFinal = date.plusDays(cantidad);
        return dateFinal;
    }
    
    public static ArrayList<LocalDate> obtenerListadoDias(HttpServletRequest request, HttpServletResponse response){
        ArrayList<LocalDate> dias = new ArrayList<LocalDate>();
        String[] fechas = new String[10];
        fechas[0] = request.getParameter("fechaDeVacacion0");
        fechas[1] = request.getParameter("fechaDeVacacion1");
        fechas[2] = request.getParameter("fechaDeVacacion2");
        fechas[3] = request.getParameter("fechaDeVacacion3");
        fechas[4] = request.getParameter("fechaDeVacacion4");
        fechas[5] = request.getParameter("fechaDeVacacion5");
        fechas[6] = request.getParameter("fechaDeVacacion6");
        fechas[7] = request.getParameter("fechaDeVacacion7");
        fechas[8] = request.getParameter("fechaDeVacacion8");
        fechas[9] = request.getParameter("fechaDeVacacion9");
        LocalDate dia = null;
        for (String fecha : fechas) {
            if((fecha == null)||(fecha.isEmpty())){
                dia = obtenerDiaAleatorio();
                while(revisarSiExisteElDia(dias, dia)){
                    dia = obtenerDiaAleatorio();
                }
                dias.add(dia);
            }else{
                dia = convertirFechaConGuion(fecha);
                if(verificarUnaSemanaDespues(dia)){
                    dias.add(dia);
                }else{
                    dias.add(sumarDia(convertirFechaConGuion(fecha), 7));
                }
            }
        }
        return dias;
    }
    
    public static LocalDate obtenerDiaAleatorio(){
        LocalDate date = null;
        int dia = obtenerNumeroAleatorio(30);
        int mes = obtenerNumeroAleatorio(12);
        int anio = 2019;
        String cadenaFecha = null;
        if(mes>9&&dia>9){
            cadenaFecha = anio+"-"+mes+"-"+dia;
        }
        if(mes<10&&dia<10){
            cadenaFecha = anio+"-0"+mes+"-0"+dia;
        }
        if(mes<10&&dia>9){
            cadenaFecha = anio+"-0"+mes+"-"+dia;
        }
        if(mes>9&&dia<10){
            cadenaFecha = anio+"-"+mes+"-0"+dia;
        }
        date = convertirFechaConGuion(cadenaFecha);
        if(date.isBefore(LocalDate.now())){
            date = sumarAnio(date, 1);
        }
        return date;
    }
    
    public static LocalDate primeroDelSiguienteMes(LocalDate date){
        int dia = 1;
        int mes = date.getMonthValue();
        int anio = date.getYear();
        String cadenaFecha = null;
        if(mes>9&&dia>9){
            cadenaFecha = anio+"-"+mes+"-"+dia;
        }
        if(mes<10&&dia<10){
            cadenaFecha = anio+"-0"+mes+"-0"+dia;
        }
        if(mes<10&&dia>9){
            cadenaFecha = anio+"-0"+mes+"-"+dia;
        }
        if(mes>9&&dia<10){
            cadenaFecha = anio+"-"+mes+"-0"+dia;
        }
        date = convertirFechaConGuion(cadenaFecha);
        return sumarMes(date, 1);
    }
    
    public static int obtenerNumeroAleatorio(int cantidad){
        int numero = (int) (Math.random() * cantidad) + 1;
        return numero;
    }
    
    public static LocalDate convertirFechaConGuion(String fecha){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formateador);
        return date;
    }
    
    public static LocalDate convertirFechaConDiagonal(String fecha){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate date = LocalDate.parse(fecha, formateador);
        return date;
    }

    public static boolean revisarSiExisteElDia(ArrayList<LocalDate> dias, LocalDate dia) {
        for (LocalDate date: dias) {
            if(dia.equals(date)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean revisarSiExisteElDia2(ArrayList<Vacacion> dias, LocalDate dia){
        for(Vacacion vacacion: dias){
            if(vacacion.getFecha().equals(dia)){
                return true;
            }
        }
        return false;
    }
    
    public static Date convertirLocalDateADate(LocalDate fecha){
        Date date = Date.valueOf(fecha);
        return date;
    }
    
    public static boolean verificarUnaSemanaDespues(LocalDate fecha){
        boolean valor = true;
        if(fecha.isBefore(sumarDia(LocalDate.now(), 7))||fecha.isEqual(sumarDia(LocalDate.now(), 7))){
            valor=false;
        }
        return valor;
    }
    
}
