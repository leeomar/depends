package depends.extractor.cpp;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import depends.deptypes.DependencyType;
import depends.extractor.java.JavaFileParser;

public class GenericTypeTest extends CppParserTest{
    @Before
    public void setUp() {
    	super.init();
    }
	
	@Test
	public void test_templateSpecializationOfStruct() throws IOException {
	    String src = "./src/test/resources/cpp-code-examples/template/TempateStructure.cpp";
	    CppFileParser parser = createParser(src);
        parser.parse();
        inferer.resolveAllBindings();
        assertNotNull(repo.getEntity("hash"));
	}

    @Test
	public void test_genericTypesVarParameterReference() throws IOException {
	    String src = "./src/test/resources/cpp-code-examples/template/GenericTypes.cpp";
	    CppFileParser parser = createParser(src);
        parser.parse();
        inferer.resolveAllBindings();
        this.assertContainsRelation(repo.getEntity("xStack"), DependencyType.PARAMETER, "X");
	}
	
    @Test
	public void test_genericTypesExtends() throws IOException {
	    String src = "./src/test/resources/cpp-code-examples/template/GenericTypes.cpp";
	    CppFileParser parser = createParser(src);
        parser.parse();
        inferer.resolveAllBindings();
        this.assertContainsRelation(repo.getEntity("XStack"), DependencyType.INHERIT, "Stack");
	}
	
    
    @Test
	public void test_GenericTypeEmbededShouldBeIdentified() throws IOException {
        String src = "./src/test/resources/cpp-code-examples/template/EmbededTemplates.cpp";
	    CppFileParser parser = createParser(src);
        parser.parse();
        inferer.resolveAllBindings();
        this.assertContainsRelation(repo.getEntity("GenericTypeEmbededTest"),
        		DependencyType.CONTAIN, "MyHashMap");
        this.assertContainsRelation(repo.getEntity("GenericTypeEmbededTest.data"),
        		DependencyType.PARAMETER, "MyList");
        this.assertContainsRelation(repo.getEntity("GenericTypeEmbededTest.data"),
        		DependencyType.PARAMETER, "MyArray");
	}
	
	

}
