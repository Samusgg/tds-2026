package es.umu.gestor.service;

import es.umu.gestor.model.Prioridad;
import es.umu.gestor.model.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorTareas {
	private final List<Tarea> tareas = new ArrayList<>();
	private int siguienteId = 1;

	public Tarea crearTarea(String titulo, String descripcion, Prioridad prioridad) {
		Tarea tarea = new Tarea(siguienteId++, titulo, descripcion, prioridad);
		tareas.add(tarea);
		return tarea;
	}

	public List<Tarea> listarTareas() {
		return List.copyOf(tareas);
	}

	public Optional<Tarea> buscarPorId(int id) {
		return tareas.stream().filter(t -> t.getId() == id).findFirst();
	}

	public boolean completarTarea(int id) {
		Optional<Tarea> tarea = buscarPorId(id);
		tarea.ifPresent(Tarea::completar);
		return tarea.isPresent();
	}

	public boolean eliminarTarea(int id) {
		return tareas.removeIf(t -> t.getId() == id);
	}

	public long contarPendientes() {
		return tareas.stream().filter(t -> !t.isCompletada()).count();
	}
}
