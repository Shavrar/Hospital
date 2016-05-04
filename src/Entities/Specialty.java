package Entities;

public class Specialty implements Entity {

	private Integer id;
    private String name; //Название
    private Integer rate; //Ставка ЗП
    private Boolean narrow; //Узкий или нет
    private Integer count; //Количество врачей
    private Integer sumcost; //Суммарные затраты

    public Integer getSumcost() {
        return sumcost;
    }

    public void setSumcost(Integer sumcost) {
        this.sumcost = sumcost;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int a) {
        this.count = a;
    }

    public Boolean getNarrow() {
        return narrow;
    }

    public void setNarrow(Boolean narrow) {
        this.narrow = narrow;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }


    @Override
	public Class getClassName() {
		return this.getClass();
	}

	@Override
	public String getStringName() {
		return "Specialty";
	}

}
