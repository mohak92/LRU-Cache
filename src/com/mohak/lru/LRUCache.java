package com.mohak.lru;

import java.util.Map;
import java.util.HashMap;

public class LRUCache {

	private int actualSize;
	private Map<Integer, Node> map;
	private DoublyLinkedList linkedList;
	
	public LRUCache() {
		this.map = new HashMap<>();
		this.linkedList = new DoublyLinkedList();
	}
	
	public void put(int id, String data) {
		
		//update the node if already exists
		if(map.containsKey(id)) {
			Node node = this.map.get(id);
			node.setData(data);
			System.out.println("Inside put: " + node);
			//update the node to be the head (cache feature)
			update(node);
			return;
		}
		
		//the data is not present in cache so insert
		Node node = new Node(id, data);
		
		//we have to insert into the cache + set it to be head node
		if(this.actualSize < Constants.CAPACITY) {
			this.actualSize++;
			add(node);
		} else {
			//or the cache is full: we have to remove the last item + insert the new one
			removeTail();
			add(node);
		}
	}
	
	//add node to the head
	private void add(Node node) {
		
	}
	
	//remove last item (least frequently used)
	private void removeTail() {
		
	}
	
	//move the given node to the front (head) of Linked List
	private void update(Node node) {
		
	}
}
