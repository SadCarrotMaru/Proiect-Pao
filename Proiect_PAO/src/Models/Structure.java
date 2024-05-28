package Models;

public class Structure {
    private int structure_id;
    private String name;
    private String description;
    private int min_generation_value;
    private int max_generation_value;
    private int w;
    private int h;

    public Structure(int structure_id, String name, String description, int min_generation_value, int max_generation_value, int w, int h)
    {
        this.structure_id = structure_id;
        this.name = name;
        this.description = description;
        this.min_generation_value = min_generation_value;
        this.max_generation_value = max_generation_value;
        this.w = w;
        this.h = h;
    }
    public int getStructure_id() {return this.structure_id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public int getMin_generation_value() {return this.min_generation_value;}
    public int getMax_generation_value() {return this.max_generation_value;}
    public int getW() {return this.w;}
    public int getH() {return this.h;}
    public void setStructure_id(int structure_id) {this.structure_id = structure_id;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setMin_generation_value(int min_generation_value) {this.min_generation_value = min_generation_value;}
    public void setMax_generation_value(int max_generation_value) {this.max_generation_value = max_generation_value;}
    public void setW(int w) {this.w = w;}
    public void setH(int h) {this.h = h;}

    @Override
    public String toString()
    {
        return "Structure [structure_id=" + structure_id + ", name=" + name + ", description=" + description + ", min_generation_value=" + min_generation_value + ", max_generation_value=" + max_generation_value + ", w=" + w + ", h=" + h + "]\n";
    }
}
