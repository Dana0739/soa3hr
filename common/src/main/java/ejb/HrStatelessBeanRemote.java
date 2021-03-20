package ejb;

import javax.ejb.Remote;

@Remote
public interface HrStatelessBeanRemote {
    int sayHello();

    String callXmlHrHireWorkerDTO(long personId, long orgId, String position, String status, String date);

    String callXmlHrFireWorkerDTO(long id);
}
