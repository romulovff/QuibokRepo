This project consists of a REST API to create Lombok files quicker to improve developer productivity.

A request body example would be the one below:

```
{
    "filename": "test",
    "isData": true,
    "isAllArgsConstructor": true,
    "isNoArgsConstructor": true,
    "properties": {
        "key1": "value1",
        "key2": "value2",
        "key3": "value3"
    }
}
```

In the example above the file name is defined by the value passed in the key <b>filename</b>, <b>key#</b> represents the name of the object's property and <b>value#</b> represents the data type. It will generate the following file:

```
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class test {

	private value1 key1;
	private value2 key2;
	private value3 key3;

}
```

