package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FILTER_INSTANCE", schema = "", catalog = "CHART_DB")
public class FilterInstanceEntity  implements Serializable {
    @EmbeddedId
    private FilterInstanceEntityPK id;
    @Basic
    @Column(name = "VALUE")
    private String value;
    @ManyToOne
    @JoinColumn(name = "FILTER_ID", referencedColumnName = "FILTER_ID",insertable = false,updatable = false)
    private FilterEntity filterByFilterId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FilterInstanceEntityPK getId() {
        return id;
    }

    public void setId(FilterInstanceEntityPK id) {
        this.id = id;
    }

    public FilterEntity getFilterByFilterId() {
        return filterByFilterId;
    }

    public void setFilterByFilterId(FilterEntity filterByFilterId) {
        this.filterByFilterId = filterByFilterId;
    }
}
