package csw.service.consumer;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import csw.service.logic.AbstractService;
import csw.utility.Address;

public abstract class AbstractServiceConsumer extends AbstractService {

	private RestTemplate restClient;

	@Value("${keycloak.base.url}")
	private String base_url;

	@PostConstruct
	public void postConstruct() {
		this.restClient = new RestTemplate();
		this.restClient.setErrorHandler(new ErrorHandler());
	}

	protected <T> T get(final ParameterizedTypeReference<T> clazz, final String resource,
			final Map<String, Object> params, Address address, String token) {
		final HttpHeaders headers = this.createHeaders(token, null);
		final HttpEntity<?> entity = new HttpEntity<>(headers);
		final HttpEntity<T> response = this.restClient.exchange(
				this.buildURIWithParams(this.uriWithResource(resource, address), params), HttpMethod.GET, entity,
				clazz);
		return response.getBody();
	}

	protected <T> T get(final ParameterizedTypeReference<T> clazz, final String resource,
			final Map<String, Object> params, Address address, HttpHeaders headers) {
		final HttpEntity<?> entity = new HttpEntity<>(headers);
		final HttpEntity<T> response = this.restClient.exchange(
				this.buildURIWithParams(this.uriWithResource(resource, address), params), HttpMethod.GET, entity,
				clazz);
		return response.getBody();
	}

	protected <T> T get(final ParameterizedTypeReference<T> clazz, final String resource, Address address,
			String token) {
		final HttpHeaders headers = this.createHeaders(token, null);
		final HttpEntity<?> entity = new HttpEntity<>(headers);
		final HttpEntity<T> response = this.restClient.exchange(this.uriWithResource(resource, address), HttpMethod.GET,
				entity, clazz);
		return response.getBody();
	}

	protected <T> T get(final ParameterizedTypeReference<T> clazz, final String resource, Address address,
			HttpHeaders headers) {
		final HttpEntity<?> entity = new HttpEntity<>(headers);
		final HttpEntity<T> response = this.restClient.exchange(this.uriWithResource(resource, address), HttpMethod.GET,
				entity, clazz);
		return response.getBody();
	}

	protected <T> T get(final Class<T> clazz, final String resource, final Object body,
			final Map<String, Object> params, Address address, String token) {
		final HttpHeaders headers = this.createHeaders(token, null);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);
		final HttpEntity<T> response = this.restClient.exchange(
				this.buildURIWithParams(this.uriWithResource(resource, address), params), HttpMethod.GET, entity,
				clazz);
		return response.getBody();
	}
	
	protected <T> int getStatus(final ParameterizedTypeReference<T> clazz, final String resource, final Object body, Address address, String token,
			MediaType mediaType) {
		final HttpHeaders headers = this.createHeaders(token, mediaType);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);		
		final ResponseEntity response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.GET, entity, clazz);
		return response.getStatusCodeValue();
	}

	protected <T> T get(final ParameterizedTypeReference<T> clazz, final String resource, final Object body,
			final Map<String, Object> params, Address address, String token) {
		final HttpHeaders headers = this.createHeaders(token, null);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);
		final HttpEntity<T> response = this.restClient.exchange(
				this.buildURIWithParams(this.uriWithResource(resource, address), params), HttpMethod.GET, entity,
				clazz);
		return response.getBody();
	}

	protected <T> T post(final Class<T> clazz, final String resource, final Object body, Address address,
			String token) {
		final HttpHeaders headers = this.createHeaders(token, null);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);
		final HttpEntity<T> response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.POST, entity, clazz);
		return response.getBody();
	}

	protected <T> T post(final Class<T> clazz, final String resource, final Object body, Address address, String token,
			MediaType mediaType) {
		final HttpHeaders headers = this.createHeaders(token, mediaType);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);
		final HttpEntity<T> response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.POST, entity, clazz);
		return response.getBody();
	}
	
	protected <T> HttpEntity<T> post(final ParameterizedTypeReference<T> clazz, final String resource, final Object body, Address address, String token,
			MediaType mediaType) {
		final HttpHeaders headers = this.createHeaders(token, mediaType);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);		
		final HttpEntity<T> response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.POST, entity, clazz);
		return response;
	}
	
	protected <T> int postStatus(final ParameterizedTypeReference<T> clazz, final String resource, final Object body, Address address, String token,
			MediaType mediaType) {
		final HttpHeaders headers = this.createHeaders(token, mediaType);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);		
		final ResponseEntity response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.POST, entity, clazz);
		return response.getStatusCodeValue();
	}
	
	protected <T> int putStatus(final ParameterizedTypeReference<T> clazz, final String resource, final Object body, Address address, String token,
			MediaType mediaType) {
		final HttpHeaders headers = this.createHeaders(token, mediaType);
		final HttpEntity<?> entity = new HttpEntity<>(body, headers);		
		final ResponseEntity response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.PUT, entity, clazz);
		return response.getStatusCodeValue();
	}
	
	protected <T> int deleteStatus(final Class<T> clazz, final String resource, Address address, String token,
			MediaType mediaType) {
		final HttpHeaders headers = this.createHeaders(token, mediaType);
		final HttpEntity<?> entity = new HttpEntity<>(null, headers);		
		final ResponseEntity response = this.restClient.exchange(this.uriWithResource(resource, address),
				HttpMethod.DELETE, entity, clazz);
		return response.getStatusCodeValue();
	}
	
	protected Map<String, Object> getMapParams(final String key, final Object obj) {
		final Map<String, Object> map = new HashMap<>();
		map.putIfAbsent(key, obj);
		return map;
	}

	private URI buildURIWithParams(final String uri, final Map<String, Object> params) {
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
		params.forEach((key, value) -> {
			builder.queryParam(key, value);
		});
		return builder.build().toUri();
	}

	private String uriWithResource(final String resource, Address address) {
		String url = null;
		switch (address) {
		case AUTH_USER:
			url = base_url;
			break;
		default:
			url = null;
			break;
		}
		return url + resource;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Collection<T> convert(final Collection iter, final Class<T> clazz) {
		final Collection<T> list = new ArrayList<>();
		list.addAll(iter);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T, Y> Map<T, Y> convert(final Map map, final Class<T> clazzT, final Class<Y> clazzY) {
		final Map<T, Y> mapToReturn = new HashMap<>(map);
		return mapToReturn;
	}

	private class ErrorHandler implements ResponseErrorHandler {

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			// Do nothing;
		}
	}

	protected HttpHeaders createHeaders(String token, MediaType mediaType) {
		final HttpHeaders headers = new HttpHeaders();
		if (MediaType.APPLICATION_FORM_URLENCODED.equals(mediaType)) {
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		} 
		headers.set("Accept", MediaType.ALL_VALUE);
		headers.setAccept(Collections.singletonList(MediaType.ALL));
		if (token != null)
			headers.set("Authorization", "Bearer " + token);
		return headers;
	}


}
