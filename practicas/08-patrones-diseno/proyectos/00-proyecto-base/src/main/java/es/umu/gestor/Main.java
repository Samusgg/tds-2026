package es.umu.gestor;

import es.umu.gestor.model.Prioridad;
import es.umu.gestor.service.GestorTareas;

public class Main {
	public static void main(String[] args) {
		GestorTareas gestor = new GestorTareas();

		System.out.println("=== GESTOR DE TAREAS : creando tareas ===");
		gestor.crearTarea("Preparar práctica", "Diseñar ejercicios de patrones", Prioridad.ALTA);
		gestor.crearTarea("Revisar entregas", "Corregir proyectos de Java", Prioridad.MEDIA);
		gestor.completarTarea(1);

		gestor.listarTareas().forEach(System.out::println);
		System.out.println("Tareas pendientes: " + gestor.contarPendientes());
		System.out.println("");

		System.out.println("=== GESTOR DE TAREAS : eliminando tarea ===");
		gestor.eliminarTarea(1);
		gestor.listarTareas().forEach(System.out::println);
		System.out.println("Tareas pendientes: " + gestor.contarPendientes());
	}
}
