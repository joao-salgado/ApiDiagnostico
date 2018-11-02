package com.diagnostico.api.repository.dto;

public class BWByProcess {

	private Double get;
	private Double use;
	private Double learn;
	private Double contribute;
	private Double evaluate;
	private Double build;
	private Double discard;
	private Double total;
	
	private Long count;

	private String name;

	public Double getGet() {
		return get;
	}

	public void setGet(Double get) {
		this.get = get;
	}

	public Double getUse() {
		return use;
	}

	public void setUse(Double use) {
		this.use = use;
	}

	public Double getLearn() {
		return learn;
	}

	public void setLearn(Double learn) {
		this.learn = learn;
	}

	public Double getContribute() {
		return contribute;
	}

	public void setContribute(Double contribute) {
		this.contribute = contribute;
	}

	public Double getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Double evaluate) {
		this.evaluate = evaluate;
	}

	public Double getBuild() {
		return build;
	}

	public void setBuild(Double build) {
		this.build = build;
	}

	public Double getDiscard() {
		return discard;
	}

	public void setDiscard(Double discard) {
		this.discard = discard;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
