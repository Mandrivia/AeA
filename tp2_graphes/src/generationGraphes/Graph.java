package generationGraphes;

import VertexExceptions.VertexAlreadyExistException;
import VertexExceptions.VertexNotFoundException;

public interface Graph {
	public void addVertex();
	public void addVertexNumber(int i) throws VertexAlreadyExistException;
	public void addEdge(Vertex v1, Vertex v2) throws VertexNotFoundException;
	public void addEdge(int i, int j) throws VertexNotFoundException;
	public Vertex getVertex(int i);
	
}
