package th.ac.kmutt.chart.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@Entity
@Table(name = "COMMENT", schema = "", catalog = "CHART_DB")
public class CommentEntity  implements Serializable {
    private String instanceId;
    private String comment;
    //private ChartInstanceEntity chartInstanceByInstanceId;

    @Id
    @Column(name = "INSTANCE_ID")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Basic
    @Column(name = "COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId != null ? instanceId.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
 /*
    @OneToOne
    @JoinColumn(name = "INSTANCE_ID", referencedColumnName = "INSTANCE_ID", nullable = false)
    public ChartInstanceEntity getChartInstanceByInstanceId() {
        return chartInstanceByInstanceId;
    }

    public void setChartInstanceByInstanceId(ChartInstanceEntity chartInstanceByInstanceId) {
        this.chartInstanceByInstanceId = chartInstanceByInstanceId;
    }*/
}
