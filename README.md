# Training of parsing files XML && JSON

Here realise two ways of XML file parsing - "DOM , SAX"

And one example of JSON parsing - "JSON Simple Parsing"

XML-file:

```
  <?xml version="1.0" encoding="UTF-8" ?>
  <root>
      <name>Test file</name>
      <person>
          <element>
              <age>12</age>
            <name>Ivan</name>
        </element>
        <element>
            <age>20</age>
            <name>Sergei</name>
        </element>
        <element>
            <age>42</age>
            <name>Alexei</name>
        </element>
        <element>
            <age>34</age>
            <name>Michael</name>
        </element>
        <element>
            <age>235</age>
            <name>Alex</name>
        </element>
        <element>
            <age>20</age>
            <name>Roman</name>
        </element>
    </person>
</root>
```
JSON-file:

```
{
  "name": "Ivan",
  "people": [
    {
      "name": "Ivan",
      "age": 12
    },
    {
      "name": "Sergei",
      "age": 20
    }, {
      "name": "Alexei",
      "age": 42
    }, {
      "name": "Michael",
      "age": 34
    }, {
      "name": "Alex",
      "age": 235
    }, {
      "name": "Roman",
      "age": 19
    }
  ]
}
```
