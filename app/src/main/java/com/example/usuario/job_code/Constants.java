/*Universidad de Costa Rica
Informática Empresarial
IF7201 - Gestión de Proyectos
Prof. MAP. Verny Fernández
Proyecto Job Code
Estudiantes:
Paula Álvarez Barrantes – B40301
Alejandra Anchía Pérez - B30388
César Bolaños Brenes - B31030
Stephanie Rojas Alfaro – A54827
I Ciclo, 2017

Clase: Constants
Clase lógica que se encarga de mantener el url de los Web Services que nunca va a cambiar, el cuál es estático,
además de mantener de la misma manera el nombre del método encargado de insertar los datos a la Base de Datos.*/
package com.example.usuario.job_code;

/**
 * Created by Usuario on 23/05/2017.
 */

public class Constants {
    public static final String ROOT_URL = "http://jobcode.azurewebsites.net/Post.svc/";
    public static final String URL_REGISTER = ROOT_URL+"addPost?";

}
