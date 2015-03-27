package generationGraphes;

import java.util.LinkedList;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public class GraphImpl implements GraphItf {

	LinkedList<Vertex> Vertex;
	LinkedList<Edge> Edges;

	public GraphImpl() {
		this.Vertex = new LinkedList<Vertex>();
		this.Edges = new LinkedList<Edge>();
	}

	public LinkedList<Vertex> getVertex() {
		return this.Vertex;
	}

	public LinkedList<Edge> getEdges() {
		return this.Edges;
	}

	public void addVertex(int n) {
		for (int i = 0; i < n; i++) {
			this.addVertex();
		}
	}

	public void addVertexWithStart(int n, int start)
			throws VertexAlreadyExistException {
		for (int i = 0; i < n; i++) {
			this.addVertexNumber(n + i);
		}
	}

	@Override
	public void addVertex() {
		Vertex newVertex = new Vertex(this.Vertex.size() - 1);
		this.Vertex.add(newVertex);
	}

	public void removeVertex(Vertex vertex) {
		this.Vertex.remove(vertex.getNumber());
	}

	@Override
	public void addVertexNumber(int i) throws VertexAlreadyExistException {
		for (int j = 0; j < this.Vertex.size(); j++) {
			if (this.Vertex.get(j).getNumber() == i)
				throw new VertexAlreadyExistException();
		}
		Vertex newVertex = new Vertex(i);
		this.Vertex.add(newVertex);
	}

	public void removeVertexNumber(int i) {
		this.Vertex.remove(i);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException {
		if (!this.Vertex.contains(v1) || !this.Vertex.contains(v2))
			throw new VertexNotFoundException();
		Edge edge = new Edge(v1, v2);
		this.Edges.add(edge);
	}

	public void addValue(Edge edge, int value) {
		edge.setValue(value);
	}

	public void addEdge(Vertex v1, Vertex v2, int value) {
		Edge edge = new Edge(v1, v2);
		edge.setValue(value);

	}

	@Override
	public void addEdge(int i, int j) throws VertexNotFoundException {
		if (i > this.Vertex.size() || j > this.Vertex.size())
			throw new VertexNotFoundException();
		Edge edge = new Edge(this.Vertex.get(i), this.Vertex.get(j));
		this.Edges.add(edge);
	}

	@Override
	public Vertex getVertex(int i) {
		return this.Vertex.get(i);
	}

	public void affiche() {
		for (int i = 0; i < this.Edges.size(); i++) {
			System.out.println(this.Edges.get(i).getStart().getNumber()
					+ "--->" + this.Edges.get(i).getEnd().getNumber()
					+ " Value : " + this.Edges.get(i).getValue());
		}
	}

	public LinkedList<Edge> getEndEdgesFromVertex(Vertex v) {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getStart() == v
					&& edge.getEnd().getNumber() > v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}
	
	public LinkedList<Edge> getStartEdgesFromVertex(Vertex v) {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.Edges) {
			if (edge.getEnd() == v
					&& edge.getStart().getNumber() > v.getNumber()) {
				edges.add(edge);
			}

		}
		return edges;
	}

	public String generateTxt() {
		String formatTxt = "";
		for (int i = 0; i < this.Vertex.size(); i++) {
			Vertex currentVertex = this.Vertex.get(i);
			LinkedList<Edge> startEdges = getStartEdgesFromVertex(currentVertex);
			LinkedList<Edge> endEdges = getEndEdgesFromVertex(currentVertex);

			if (!startEdges.isEmpty() || !endEdges.isEmpty()) {
				formatTxt += this.Vertex.get(i).getNumber() + " ";
				for (Edge edge : startEdges) {
						formatTxt += edge.getStart().getNumber() + " "
								+ edge.getValue() + " ";
				}
				for (Edge edge : endEdges) {
					formatTxt += edge.getEnd().getNumber() + " " + edge.getValue() + " "; 
				}
				formatTxt += "\n";
			}
		}

		return formatTxt;
	}

}
