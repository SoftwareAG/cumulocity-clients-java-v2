// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Information about paging statistics.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PageStatistics {

	/**
	 * The current page of the paginated results.
	 */
	private int currentPage;

	/**
	 * Indicates the number of objects that the collection may contain per page. The upper limit for one page is 2,000 objects.
	 */
	private int pageSize;

	/**
	 * The total number of results (elements).
	 */
	private int totalElements;

	/**
	 * The total number of paginated results (pages).
	 * 
	 * > **&#9432; Info:** This property is returned by default except when an operation retrieves all records where values are between an upper and lower boundary, for example, querying ranges using `dateFrom`–`dateTo`. In such cases, the query parameter `withTotalPages=true` should be used to include the total number of pages (at the expense of slightly slower performance).
	 * 
	 */
	private int totalPages;

	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalElements() {
		return totalElements;
	}
	
	public void setTotalElements(final int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public String toString() {
		try {
			// TODO thats an extensive operation, which only helps debugging
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof PageStatistics) {
			PageStatistics comparer = (PageStatistics) r;
			if (Integer.valueOf(comparer.getCurrentPage()).equals(Integer.valueOf(this.getCurrentPage())) && Integer.valueOf(comparer.getPageSize()).equals(Integer.valueOf(this.getPageSize())) && Integer.valueOf(comparer.getTotalElements()).equals(Integer.valueOf(this.getTotalElements())) && Integer.valueOf(comparer.getTotalPages()).equals(Integer.valueOf(this.getTotalPages()))) {
				return true;
			}
		}
		return false;
	}
}
