package th.ac.kmutt.chart.fusion.model;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imake on 21/10/2015.
 */
public class CategoriesFusionM implements Serializable{

@SerializedName("category")
    @XStreamAlias("category")
    private List<CategoryM> category;

        public List<CategoryM> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryM> category) {
            this.category = category;
        }
}
