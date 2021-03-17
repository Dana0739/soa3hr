package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name="HRHireFireWorkerDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class HRHireFireWorkerDTO {
    @XmlElement
    Long organizationId;
    @XmlElement
    Position position;
    @XmlElement
    Status status;
    @XmlElement
    Date startDate;
    @XmlElement
    Date endDate;

    public HRHireFireWorkerDTO() {
        this.endDate = new Date();
    }

    public HRHireFireWorkerDTO(Long orgId, Position position, Status status, Date startDate) {
        this.organizationId = orgId;
        this.position = position;
        this.status = status;
        this.startDate = startDate;
    }
}
