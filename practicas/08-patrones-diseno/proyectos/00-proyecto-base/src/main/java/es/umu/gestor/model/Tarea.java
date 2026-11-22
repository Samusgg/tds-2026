package es.umu.gestor.model;

import java.time.LocalDate;

public class Tarea {
	private final int id;
	private final String titulo;
	private final String descripcion;
	private final Prioridad prioridad;
	private boolean completada;
	private final LocalDate fechaLimite;

	public Tarea(int id, String titulo, String descripcion, Prioridad prioridad) {
		this(id, titulo, descripcion, prioridad, null);
	}

	public Tarea(int id, String titulo, String descripcion, Prioridad prioridad, LocalDate fechaLimite) {
		if (titulo == null || titulo.isBlank()) {
			throw new IllegalArgumentException("El título es obligatorio");
		}
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion == null ? "" : descripcion;
		this.prioridad = prioridad == null ? Prioridad.MEDIA : prioridad;
		this.fechaLimite = fechaLimite;
		this.completada = false;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public boolean isCompletada() {
		return completada;
	}

	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void completar() {
		completada = true;
	}

	@Override
	public String toString() {
		return String.format("#%d [%s] %s (%s)%s", id, prioridad, titulo, completada ? "COMPLETADA" : "PENDIENTE",
				fechaLimite == null ? "" : " - vence: " + fechaLimite);
	}
}
