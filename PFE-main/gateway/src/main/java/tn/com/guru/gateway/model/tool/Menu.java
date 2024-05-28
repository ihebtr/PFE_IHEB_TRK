package tn.com.guru.gateway.model.tool;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {

	private String path;
	private String title;
	private String titleAr;
	private String titleFr;
	private String icon;
	private String classMethod;
	private String badge;
	private String badgeClass;
	private Boolean isExternalLink;
	private List<Menu> submenu;
	private String idAdmFunc;
	private String idParent;
	private String labelTitleFr;
	private String labelTitleAr;

}
