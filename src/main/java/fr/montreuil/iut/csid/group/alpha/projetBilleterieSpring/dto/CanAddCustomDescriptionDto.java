package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

public class CanAddCustomDescriptionDto {
	
	private boolean canAddDescription;
	private boolean descriptionAlreadyExist;
	private String content;
	
	public CanAddCustomDescriptionDto() {}

	public boolean isCanAddDescription() {
		return canAddDescription;
	}

	public void setCanAddDescription(boolean canAddDescription) {
		this.canAddDescription = canAddDescription;
	}

	public boolean isDescriptionAlreadyExist() {
		return descriptionAlreadyExist;
	}

	public void setDescriptionAlreadyExist(boolean descriptionAlreadyExist) {
		this.descriptionAlreadyExist = descriptionAlreadyExist;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
