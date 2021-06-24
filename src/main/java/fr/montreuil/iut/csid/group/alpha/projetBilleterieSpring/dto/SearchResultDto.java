package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.List;
/**
 * SOLID: This class expose only data for search object
 */
public class SearchResultDto<T> {
	private String searched;
	private int numberFound;
	private List<T> dataList;
	private int currentPage;
	private int numberOfPages;
	
	public SearchResultDto(String searched,int numberFound,List<T> dataList,int currentPage,int numberOfPages) {
		this.searched =searched;
		this.numberFound = numberFound;
		this.dataList = dataList;
		this.currentPage = currentPage;
		this.numberOfPages = numberOfPages;
	}

	public String getSearched() {
		return searched;
	}

	public void setSearched(String searched) {
		this.searched = searched;
	}

	public int getNumberFound() {
		return numberFound;
	}

	public void setNumberFound(int numberFound) {
		this.numberFound = numberFound;
	}

	public List<T> getEventList() {
		return dataList;
	}

	public void setEventList(List<T> eventList) {
		this.dataList = eventList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
}