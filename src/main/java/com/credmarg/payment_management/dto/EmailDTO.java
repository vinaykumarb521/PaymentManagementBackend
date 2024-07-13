package com.credmarg.payment_management.dto;

public class EmailDTO {

	private Long vendorId;

	private String agentName;

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailDTO [vendorId=");
		builder.append(vendorId);
		builder.append(", agentName=");
		builder.append(agentName);
		builder.append("]");
		return builder.toString();
	}

}
