package ejb;

import model.HRHireFireWorkerDTO;
import model.Position;
import model.Status;

import javax.ejb.Stateless;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless(name = "HrStatelessBean")
public class HrStatelessBean implements HrStatelessBeanRemote {
    private Client client;
    private static final String SOA_CRUD_SERV_URL = "https://localhost:34341/lab3springapplication/workers/";

    public void init() throws KeyManagementException, NoSuchAlgorithmException {
        TrustManager[] noopTrustManager = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
        SSLContext sc = SSLContext.getInstance("ssl");
        sc.init(null, noopTrustManager, null);
        this.client = ClientBuilder.newBuilder().sslContext(sc).build();
    }

    private int requestCrudSpringService(long id, HRHireFireWorkerDTO hrHireFireWorkerDTO) {
//        HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);
//
//        // Create a trust manager that does not validate certificate chains
//        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
//            public X509Certificate[] getAcceptedIssuers(){return null;}
//            public void checkClientTrusted(X509Certificate[] certs, String authType){}
//            public void checkServerTrusted(X509Certificate[] certs, String authType){}
//        }};
//
//        // Install the all-trusting trust manager
//        try {
//            SSLContext sc = SSLContext.getInstance("TLS");
//            sc.init(null, trustAllCerts, new SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        } catch (Exception e) {
//            for (int i = 0; i < e.getStackTrace().length; i++) {
//                System.out.println(e.getStackTrace()[i].toString());
//            }
//        }

        try {
            this.init();
            String rest_uri = System.getenv("SOA_CRUD_SERV_URL");
            if (rest_uri == null || rest_uri.isEmpty()) rest_uri = SOA_CRUD_SERV_URL;
            return client.target(rest_uri + id).request(MediaType.APPLICATION_XML)
                    .post(Entity.entity(hrHireFireWorkerDTO, MediaType.APPLICATION_XML)).getStatus();
        } catch (Exception e) {
            for (int i = 0; i < e.getStackTrace().length; i++) {
                System.out.println(e.getStackTrace()[i].toString());
            }
        }
        return -1;
    }

    @Override
    public int sayHello() {
        return 200;
    }

    @Override
    public int callXmlHrHireWorkerDTO(long personId, long orgId, String position, String status, String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(date);
            return this.requestCrudSpringService(personId,
                    new HRHireFireWorkerDTO(orgId, Position.getByTitle(position),
                            Status.getByTitle(status), startDate));
        } catch (IllegalArgumentException | ParseException e) {
            for (int i = 0; i < e.getStackTrace().length; i++) {
                System.out.println(e.getStackTrace()[i].toString());
            }
            return 422;
        } catch (Exception e) {
            for (int i = 0; i < e.getStackTrace().length; i++) {
                System.out.println(e.getStackTrace()[i].toString());
            }
            return 500;
        }
    }

    @Override
    public int callXmlHrHireWorkerDTO(long id) {
        return this.requestCrudSpringService(id, new HRHireFireWorkerDTO());
    }
}
