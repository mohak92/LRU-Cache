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
		
		//the node will be the new head: so update accordingly
		node.setNextNode(this.linkedList.getHeadNode());
		//it is the first node: no previous node
		node.setPreviousNode(null);
		
		//we have to update the previous head: point to the new head (which is the node)
		if(linkedList.getHeadNode() != null)
			linkedList.getHeadNode().setPreviousNode(node);
		
		//update the head node
		linkedList.setHeadNode(node);
		
		//if there is 1 node in the list it is the head as well as tail
		if(linkedList.getTailNode() == null)
			linkedList.setTailNode(node);
		
		//we have to update the map with the node
		this.map.put(node.getId(), node);
		
	}
	
	//remove last item (least frequently used)
	private void removeTail() {
		
		//get node from the map
		Node lastNode = this.map.get(this.linkedList.getTailNode().getId());
		
		//new tail node is the previous node (because we remove the actual one)
		this.linkedList.setTailNode(linkedList.getTailNode().getPreviousNode());
		
		//set the next node to be NULL: because it is the right most item
		if(this.linkedList.getTailNode() != null)
			this.linkedList.getTailNode().setNextNode(null);
		
		//avoid obsolete references
		lastNode = null;
	}
	
	//get the item with ID id + move to the head because we want to use this item
	public Node get(int id) {
		
		//the map does not contain the item [O(1) running time!!!]
		if(!this.map.containsKey(id)) return null;
		
		//the map contains the item
		Node node = this.map.get(id);
		//move to head (frequently visited items must be close to the head)
		update(node);
		return node;
	}
	
	//move the given node to the front (head) of Linked List
	private void update(Node node) {
		
	}
	
	public void show() {
		
	}
}
