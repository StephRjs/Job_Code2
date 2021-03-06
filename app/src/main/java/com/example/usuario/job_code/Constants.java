/*Universidad de Costa Rica
Informática Empresarial
IF7201 - Gestión de Proyectos
Prof. MAP. Verny Fernández
Proyecto Job Code
Estudiantes:
Paula Alvarez Barrantes – B40301
Alejandra Anchía Pérez - B30388
César Bolaños Brenes - B31030
Stephanie Rojas Alfaro – A54827
I Ciclo, 2017

Clase: Constants
Clase lógica que se encarga de mantener el url de los Web Services que nunca va a cambiar, el cuál es estático,
además de mantener de la misma manera el nombre del método encargado de insertar los datos a la Base de Datos.*/
package com.example.usuario.job_code;

/**
 *
 */

public class Constants {
    /**
     * Atributos estáticos de los web services.
     */
    public static final String ROOT_URL = "http://jobcode.azurewebsites.net/Post.svc/";
    public static final String URL_REGISTER = ROOT_URL+"addPost?";
    public static final String ROOT_URLL = "http://jobcode.azurewebsites.net/Login.svc/";
    public static final String URL_REGISTERSin = ROOT_URLL+"signUp?";
    public static final String URL_REGISTERCOMPLI = ROOT_URLL+"completeS?";
    public static final String URL_REGISTERLoginEx = ROOT_URLL+"loginExistsS?";
    public static final String URL_AllStudents = ROOT_URLL+"allstudents";
    public static final String URL_UpdateStudent = ROOT_URLL+"updateStudent?";
    public static final String URL_GETALLPOST = "http://jobcode.azurewebsites.net/Post.svc/getAllPosts?";
    public static final String ROOT_URLcode = "http://jobcode.azurewebsites.net/Code.svc/";
    public static final String URLRandomCode = ROOT_URLcode + "existRandomCode?";
    public static final String URL_INSERTCODE = ROOT_URLcode+"insertRandomCode?";
    public static final String URL_FORGOTPASS = ROOT_URLL+"passwordRecovery?";
    public static final String URL_PUSH = "http://jobcode.azurewebsites.net/Login.svc/sendPush?";
    public static final String SENDER_ID = "170669006092";

}
