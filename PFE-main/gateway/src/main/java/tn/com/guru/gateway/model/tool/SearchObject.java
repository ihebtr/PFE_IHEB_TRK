package tn.com.guru.gateway.model.tool;

import java.util.List;

import lombok.Data;

@Data
public class SearchObject {

	private Pagination pagination;
	private Sort sort;
	private List<Sort> listSort;
	private List<CriteriaSearch> dataSearch;
	private List<String> listCol;

}
