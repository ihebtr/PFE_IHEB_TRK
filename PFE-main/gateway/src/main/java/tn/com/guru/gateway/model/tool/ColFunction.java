package tn.com.guru.gateway.model.tool;

import java.util.List;

import lombok.Data;

@Data
public class ColFunction {
	
	private Long id;
	private String nameMenu;
	private String icon;
	private String router;
	private List<ColFunction> listCol;

}
