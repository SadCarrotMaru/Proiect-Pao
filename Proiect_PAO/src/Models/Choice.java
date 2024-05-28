package Models;

public class Choice
{
    private int choice_id;
    private String structure_type_1;
    private String structure_type_2;
    private String chosen_structure;

    public Choice(int choice_id, String structure_type_1, String structure_type_2, String chosen_structure)
    {
        this.choice_id = choice_id;
        this.structure_type_1 = structure_type_1;
        this.structure_type_2 = structure_type_2;
        this.chosen_structure = chosen_structure;
    }

    public int getChoice_id() {
        return this.choice_id;
    }
    public String getStructure_type_1() {return this.structure_type_1;}
    public String getStructure_type_2() {return this.structure_type_2;}
    public String getChosen_structure() {return this.chosen_structure;}
    public void setChoice_id(int choice_id) {this.choice_id = choice_id;}
    public void setStructure_type_1(String structure_type_1) {this.structure_type_1 = structure_type_1;}
    public void setStructure_type_2(String structure_type_2) {this.structure_type_2 = structure_type_2;}
    public void setChosen_structure(String chosen_structure) {this.chosen_structure = chosen_structure;}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString()
    {
        return "Choice [choice_id=" + this.choice_id + ", structure_type_1=" + this.structure_type_1 + ", structure_type_2=" + this.structure_type_2 + ", chosen_structure=" + this.chosen_structure + "]\n";
    }
}
