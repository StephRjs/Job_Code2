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

Clase: VolleyS
Clase lógica que se encarga de realizar un Singleton para la utilización e inicialización de la instancia
de Volley, librería que se encarga del consumo de Web Services externos.*/
package com.example.usuario.job_code;

 import android.content.Context;
 import com.android.volley.RequestQueue;
 import com.android.volley.toolbox.Volley;

 public class VolleyS {
 private static VolleyS mVolleyS = null;
 private RequestQueue mRequestQueue;

 private VolleyS(Context context) {
 mRequestQueue = Volley.newRequestQueue(context);
 }

 public static VolleyS getInstance(Context context) {
 if (mVolleyS == null) {
 mVolleyS = new VolleyS(context);
 }
 return mVolleyS;
 }

 public RequestQueue getRequestQueue() {
 return mRequestQueue;
 }

 }