package com.TruNarrative.TruNarrativeSpringExercise;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SearchApiControllerTest {
	static WireMockServer mockHttpServer = new WireMockServer(10000);

	final static String EXPECTED_OFFICER_BODY = "{\"etag\":\"e6511f821ba7d9f130553dd6f4ca5503f92d413d\",\"links\":{\"self\":\"/company/06500244/officers\"},\"kind\":\"officer-list\",\"items_per_page\":35,\"items\":[{\"address\":{\"premises\":\"5\",\"postal_code\":\"SW20 0DP\",\"country\":\"England\",\"locality\":\"London\",\"address_line_1\":\"Cranford Close\"},\"name\":\"BOXALL, Sarah Victoria\",\"appointed_on\":\"2008-02-11\",\"officer_role\":\"secretary\",\"links\":{\"officer\":{\"appointments\":\"/officers/t3kASKOIrQoe-k_JpPyJ7LYEAqE/appointments\"}}},{\"address\":{\"premises\":\"5\",\"postal_code\":\"SW20 0DP\",\"country\":\"England\",\"locality\":\"London\",\"address_line_1\":\"Cranford Close\"},\"name\":\"BRAY, Simon Anton\",\"appointed_on\":\"2008-02-11\",\"officer_role\":\"director\",\"links\":{\"officer\":{\"appointments\":\"/officers/43iRdrOR91V6QdAb0PMDS3eEGg8/appointments\"}},\"date_of_birth\":{\"month\":1,\"year\":1967},\"occupation\":\"Builder\",\"country_of_residence\":\"England\",\"nationality\":\"English\"},{\"address\":{\"postal_code\":\"M7 4AS\",\"locality\":\"Manchester\",\"address_line_1\":\"39a Leicester Road\",\"address_line_2\":\"Salford\"},\"name\":\"FORM 10 SECRETARIES FD LTD\",\"appointed_on\":\"2008-02-11\",\"officer_role\":\"corporate-nominee-secretary\",\"links\":{\"officer\":{\"appointments\":\"/officers/Yg4rTn5QucYg_hJOxGTnx3B51WY/appointments\"}},\"resigned_on\":\"2008-02-12\"},{\"address\":{\"postal_code\":\"M7 4AS\",\"locality\":\"Manchester\",\"address_line_1\":\"39a Leicester Road\",\"address_line_2\":\"Salford\"},\"name\":\"FORM 10 DIRECTORS FD LTD\",\"appointed_on\":\"2008-02-11\",\"officer_role\":\"corporate-nominee-director\",\"links\":{\"officer\":{\"appointments\":\"/officers/aDjhOpnMaB_uAHDxRnMLWpa9C-I/appointments\"}},\"resigned_on\":\"2008-02-12\"}],\"active_count\":2,\"total_results\":4,\"resigned_count\":2}";

	final static String EXPECTED_COMPANY_BODY = "{\"page_number\":1,\"kind\":\"search#companies\",\"total_results\":1,\"items\":[{\"company_status\":\"active\",\"address_snippet\":\"C/O Cottons, 361 Hagley Road, Edgbaston, B17 8DL\",\"date_of_creation\":\"1983-07-05\",\"description\":\"01736868 - Incorporated on  5 July 1983\",\"links\":{\"self\":\"/company/01736868\"},\"company_number\":\"01736868\",\"title\":\"CHADBURY MANOR MANAGEMENT COMPANY LIMITED\",\"company_type\":\"ltd\",\"address\":{\"premises\":\"C/O Cottons\",\"postal_code\":\"B17 8DL\",\"locality\":\"Edgbaston\",\"address_line_1\":\"361 Hagley Road\"},\"kind\":\"searchresults#company\",\"description_identifier\":[\"incorporated-on\"]}]}";

	final static String COMPANY_API_ENDPOINT = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Search";
	final static String OFFICERS_API_ENDPOINT = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers";
	final static String API_KEY = "";


	@Before
	public static void setup() throws Exception {
		mockHttpServer.stubFor(get(urlPathMatching(COMPANY_API_ENDPOINT)).willReturn(aResponse().withBody(EXPECTED_COMPANY_BODY).withStatus(200)));
		mockHttpServer.stubFor(get(urlPathMatching(OFFICERS_API_ENDPOINT)).willReturn(aResponse().withBody(EXPECTED_OFFICER_BODY).withStatus(200)));
		mockHttpServer.start();
	}

	@After
	public static void teardown() throws Exception {
		mockHttpServer.stop();
	}

	@Test
	public void testCompanyApiCall() throws Exception {
		String url = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1" + "/Search?Query=" + 06500244;
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-api-key", API_KEY);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		Assertions.assertEquals(EXPECTED_COMPANY_BODY, response.getBody());
	}

	@Test
	public void testOfficerApi() throws Exception {
		String url = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/Officers?CompanyNumber=06500244";
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-api-key", API_KEY);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		Assertions.assertEquals(EXPECTED_OFFICER_BODY, response.getBody());
	}
}
