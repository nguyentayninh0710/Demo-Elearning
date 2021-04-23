package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="courses")
public class Courses {//Bài giảng
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "letures_count")
	private int leturesCount; //Số bài giảng
	
	@Column(name = "hour_count")
	private int hourCount; 
	
	@Column(name = "view_count")
	private int viewCount;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "promotion_price")
	private float promotionPrice;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courses", updatable = false, insertable = false)
	private Courses mtoCategories;
	
	@OneToMany(mappedBy = "mtoCoursesFromTarget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Targets> targets;
	
	@OneToMany(mappedBy = "mtoCoursesFromVideo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Video> videos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLeturesCount() {
		return leturesCount;
	}

	public void setLeturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
	}

	public int getHourCount() {
		return hourCount;
	}

	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Courses() {
		super();
	}

	public Courses(int id, String title, String image, int leturesCount, int hourCount, int viewCount, float price,
			float discount, float promotionPrice, String description, String content, int categoryId,
			String lastUpdate) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.leturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.lastUpdate = lastUpdate;
	}

	public List<Targets> getTargets() {
		return targets;
	}

	public void setTargets(List<Targets> targets) {
		this.targets = targets;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	
	
}
