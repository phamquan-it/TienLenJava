package com.javagame.entities;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
public class Card implements Comparable<Card>{
    private int categoryid;
    private int id;
    private String name;
    private String image_link;
    public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Card(int categoryid, int id, String name, String image_link) {
		super();
		this.categoryid = categoryid;
		this.id = id;
		this.name = name;
		this.image_link = image_link;
	}
	@XmlAttribute(name = "cid")
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	@XmlAttribute(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name = "image_link")
	public String getImage_link() {
		return image_link;
	}
	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}
	@Override
	public int compareTo(Card other) {
		// TODO Auto-generated method stub
		int compareId = Integer.compare(this.getId(), other.getId());
		if(compareId == 0) {
			return Integer.compare(this.getCategoryid(), other.getCategoryid());
		}
		return compareId;
	}
	
    
}

