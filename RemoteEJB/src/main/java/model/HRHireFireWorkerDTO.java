package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HRHireFireWorkerDTO {
    @XmlElement
    Long organizationId;
    @XmlElement
    Position position;
    @XmlElement
    Status status;
    @XmlElement
    String startDate;
    @XmlElement
    String endDate;

    public HRHireFireWorkerDTO() {
        Date ed = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.endDate = dateFormat.format(ed);
    }

    public HRHireFireWorkerDTO(Long orgId, Position position, Status status, String startDate) {
        this.organizationId = orgId;
        this.position = position;
        this.status = status;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "<HRHireFireWorkerDTO>"
                + "<organizationId>" + ((this.organizationId == null) ? "" : this.organizationId) + "</organizationId>"
                + "<position>" + ((this.position == null) ? "" : this.position) + "</position>"
                + "<status>" + ((this.status == null) ? "" : this.status) + "</status>"
                + "<startDate>" + ((this.startDate == null) ? "" : this.startDate) + "</startDate>"
                + "<endDate>" + ((this.endDate == null) ? "" : this.endDate) + "</endDate>"
                + "</HRHireFireWorkerDTO>";
    }
}
