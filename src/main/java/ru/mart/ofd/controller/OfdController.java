package ru.mart.ofd.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.mart.ofd.exeptions.NotFoundException;



@RestController
@RequestMapping("kkt")
public class OfdController {
	private int count = 4;
	private Map<String, String> map1 = Stream.of("zn","model").collect(Collectors.toMap(Function.identity(),i -> i.contains("zn")? "1" : "one"));
	private Map<String, String> map2 = Stream.of("zn","model").collect(Collectors.toMap(Function.identity(),i -> i.contains("zn")? "2" : "two"));
	private Map<String, String> map3 = Stream.of("zn","model").collect(Collectors.toMap(Function.identity(),i -> i.contains("zn")? "3" : "three"));
	private List<Map<String,String>> kktList = Stream.of(map1,map2,map3).collect(Collectors.toList());
	
	@GetMapping
	public List<Map<String,String>>  getList() {
		return kktList;
	}
	
	@GetMapping("{id}")
	public Map<String,String> getKkt( @PathVariable String id ) {
		Map<String,String> kktDb = getkt(id);
		return kktDb;
	}
	
	@PostMapping
	public  Map<String,String> create (@RequestBody  Map<String,String> kkt) {
		kkt.put("zn", String.valueOf(count++));
		kktList.add(kkt);
		
		return kkt;
	}
	
	@PutMapping("{id}")
	public  Map<String,String> update(@PathVariable String id, @RequestBody Map<String, String> kkt) {		
		Map<String,String> kktDb = getkt(id);
		
		kktDb.putAll(kkt);
		kktDb.put("zn", id);
		
		return kktDb;		
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		
		Map<String,String> kktDb = getKkt(id);
		kktList.remove(kktDb);
	}
	
	
	private Map<String,String> getkt(String id) {
		
		return kktList.stream().
				filter(m -> m.containsValue(id)).
				findFirst().
				orElseThrow(NotFoundException::new);
	}
	
	
	
	
	
	
	
	
	
	
	
}
