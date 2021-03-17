package ejb;

import javax.ejb.Remote;

@Remote
public interface HrStatelessBeanRemote {
    int sayHello();

    int callXmlHrHireWorkerDTO(long personId, long orgId, String position, String status, String date);

    int callXmlHrHireWorkerDTO(long id);
}
