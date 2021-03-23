module ionisStmJavaFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.media;
	requires jdk.compiler;
	
	opens com.ionisStm.JavaFX.objets;
	opens com.ionisStm.JavaFX.vues;

	exports com.ionisStm.JavaFX;
}
