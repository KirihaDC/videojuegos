package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Torus;
import com.jme3.system.AppSettings;
import com.jme3.system.Timer;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class Main extends SimpleApplication {
    private Geometry mercurio;
    private Geometry sol;
    private Geometry venus;
    private Geometry tierra;
    private Geometry luna;
    private Geometry marte;
    private Geometry jupiter;
    private Geometry saturno;
    private Geometry urano;
    private Geometry neptuno;
    private Geometry pluton;
    private Geometry anillosSaturno;
    private Node tierraNode;
    private Timer timer;

    public static void main(String[] args) {
         AppSettings settings = new AppSettings(true);
        settings.setTitle("Sistema Solar");
        settings.setSettingsDialogImage("Interface/Planetas.jpg");
        settings.setResolution(2, 2);
        
        Main app = new Main();
        app.setSettings(settings);
        app.start();
        
       
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(20);
        timer = getTimer();
        
        // Crear estrellas
        int numEstrellas = 1000; // Cantidad de estrellas
        float radioCielo = 100f; // Radio del cielo donde se dispersarán las estrellas

        for (int i = 0; i < numEstrellas; i++) {
            // Generar una posición aleatoria en el cielo
            float x = (FastMath.nextRandomFloat() * 2 - 1) * radioCielo;
            float y = (FastMath.nextRandomFloat() * 2 - 1) * radioCielo;
            float z = (FastMath.nextRandomFloat() * 2 - 1) * radioCielo;

            // Crear una esfera pequeña para representar la estrella
            Sphere estrella = new Sphere(6, 6, 0.1f);
            Geometry estrellaGeom = new Geometry("Estrella" + i, estrella);
            Material estrellaMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            estrellaMat.setColor("Color", ColorRGBA.White); // Puedes cambiar el color si lo deseas
            estrellaGeom.setMaterial(estrellaMat);
            estrellaGeom.setLocalTranslation(x, y, z);

            // Agregar la estrella a la escena
            rootNode.attachChild(estrellaGeom);
        }

        
        // Crear el sol
        Sphere esferaSol = new Sphere(32, 32, 5f);
        sol = new Geometry("esferaSol", esferaSol);

        Material materialSol = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        materialSol.setTexture("DiffuseMap", assetManager.loadTexture("Textures/sol.jpg"));
        materialSol.setBoolean("UseMaterialColors", true);
        materialSol.setColor("Ambient", ColorRGBA.White);
        materialSol.setColor("Diffuse", ColorRGBA.White);
        materialSol.setColor("Specular", ColorRGBA.White);
        materialSol.setFloat("Shininess", 100); // Ajusta este valor para cambiar la intensidad del brillo

        sol.setMaterial(materialSol);
        rootNode.attachChild(sol);

        // Crear una fuente de luz direccional
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White); // Color de la luz
        sun.setDirection(new Vector3f(-1, -1, -1).normalizeLocal()); // Dirección de la luz

        // Agregar la luz a la escena
        rootNode.addLight(sun);

        // Crear una luz ambiental con color naranja e intensidad aumentada
        AmbientLight ambient = new AmbientLight();
        ColorRGBA orange = new ColorRGBA(1.0f, 0.5f, 0.0f, 1.0f); // Color naranja
        float intensity = 10.0f; // Factor de intensidad

        // Ajustar la intensidad multiplicando el color por el factor
        ambient.setColor(orange.mult(intensity));

        // Agregar la luz ambiental a la escena
        rootNode.addLight(ambient);


        // Crear mercurio 
        Sphere esferaMercurio = new Sphere(16, 16, 0.2f);
        mercurio = new Geometry("esferaMercurio", esferaMercurio);
        Material materialMercurio = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureMercurio = assetManager.loadTexture("Textures/mercurio.jpeg");
        materialMercurio.setTexture("ColorMap", textureMercurio);
        textureMercurio.setWrap(WrapMode.Repeat);
        mercurio.setMaterial(materialMercurio);
        rootNode.attachChild(mercurio);

        // Crear venus
        Sphere esferaVenus = new Sphere(16, 16, 0.7f);
        venus = new Geometry("esferaVenus", esferaVenus);
        Material materialVenus = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureVenus = assetManager.loadTexture("Textures/venus.jpeg");
        materialVenus.setTexture("ColorMap", textureVenus);
        textureVenus.setWrap(WrapMode.Repeat);
        venus.setMaterial(materialVenus);
        rootNode.attachChild(venus);

        // Crear la Tierra
        Sphere esferaTierra = new Sphere(16, 16, 0.9f);
        tierra = new Geometry("esferaTierra", esferaTierra);
        Material materialTierra = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureTierra = assetManager.loadTexture("Textures/tierra.jpeg");
        materialTierra.setTexture("ColorMap", textureTierra);
        textureTierra.setWrap(WrapMode.Repeat);
        tierra.setMaterial(materialTierra);
        tierraNode = new Node("TierraNode");
        tierraNode.attachChild(tierra);
        rootNode.attachChild(tierraNode);
        
         // Crear la Luna
        Sphere esferaLuna = new Sphere(16, 16, 0.3f);
        luna = new Geometry("esferaLuna", esferaLuna);
        Material materialLuna = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureLuna = assetManager.loadTexture("Textures/luna.jpeg");
        materialLuna.setTexture("ColorMap", textureLuna);
        textureLuna.setWrap(WrapMode.Repeat);
        luna.setMaterial(materialLuna);
        tierraNode.attachChild(luna);

        // Crear Marte
        Sphere esferaMarte = new Sphere(16, 16, 0.8f);
        marte = new Geometry("esferaMarte", esferaMarte);
        Material materialMarte = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureMarte = assetManager.loadTexture("Textures/mars.jpeg");
        materialMarte.setTexture("ColorMap", textureMarte);
        textureMarte.setWrap(WrapMode.Repeat);
        marte.setMaterial(materialMarte);
        rootNode.attachChild(marte);

        // Crear Júpiter
        Sphere esferaJupiter = new Sphere(16, 16, 4f);
        jupiter = new Geometry("esferaJupiter", esferaJupiter);
        Material materialJupiter = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureJupiter = assetManager.loadTexture("Textures/jupiter.jpeg");
        materialJupiter.setTexture("ColorMap", textureJupiter);
        textureJupiter.setWrap(WrapMode.Repeat);
        jupiter.setMaterial(materialJupiter);
        rootNode.attachChild(jupiter);

        // Crear Saturno
        Sphere esferaSaturno = new Sphere(32, 32, 4f);
        saturno = new Geometry("esferaSaturno", esferaSaturno);
        Material materialSaturno = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureSaturno = assetManager.loadTexture("Textures/saturno.png");
        materialSaturno.setTexture("ColorMap", textureSaturno);
        textureSaturno.setWrap(WrapMode.Repeat);
        saturno.setMaterial(materialSaturno);
        rootNode.attachChild(saturno);
        
        // Crear anillos de Saturno
        Torus torus = new Torus(32, 32, 0.5f, 10f);
        anillosSaturno = new Geometry("anillosSaturno", torus);
        Material materialAnillosSaturno = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureAnillosSaturno = assetManager.loadTexture("Textures/anillos_saturno.jpg");
        materialAnillosSaturno.setTexture("ColorMap", textureAnillosSaturno);
        textureAnillosSaturno.setWrap(WrapMode.Repeat);
        anillosSaturno.setMaterial(materialAnillosSaturno);
        rootNode.attachChild(anillosSaturno);

        // Crear Urano
        Sphere esferaUrano = new Sphere(16, 16, 3f);
        urano = new Geometry("esferaUrano", esferaUrano);
        Material materialUrano = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureUrano = assetManager.loadTexture("Textures/urano.jpeg");
        materialUrano.setTexture("ColorMap", textureUrano);
        textureUrano.setWrap(WrapMode.Repeat);
        urano.setMaterial(materialUrano);
        rootNode.attachChild(urano);

        // Crear Neptuno
        Sphere esferaNeptuno = new Sphere(16, 16, 2.5f);
        neptuno = new Geometry("esferaNeptuno", esferaNeptuno);
        Material materialNeptuno = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture textureNeptuno = assetManager.loadTexture("Textures/neptuno.jpeg");
        materialNeptuno.setTexture("ColorMap", textureNeptuno);
        textureNeptuno.setWrap(WrapMode.Repeat);
        neptuno.setMaterial(materialNeptuno);
        rootNode.attachChild(neptuno);

        // Crear Plutón
        Sphere esferaPluton = new Sphere(16, 16, 0.5f);
        pluton = new Geometry("esferaPluton", esferaPluton);
        Material materialPluton = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture texturePluton = assetManager.loadTexture("Textures/pluton.jpeg");
        materialPluton.setTexture("ColorMap", texturePluton);
        texturePluton.setWrap(WrapMode.Repeat);
        pluton.setMaterial(materialPluton);
        rootNode.attachChild(pluton);
        

    }

    @Override
    public void simpleUpdate(float tpf) {
        // Posicion del sol
        Vector3f centro = rootNode.getChild("esferaSol").getWorldTranslation();

        float radioOrbita = 3f;

        float time = timer.getTimeInSeconds() * 3f;
        float angle = time * 0.1f;
        
        // Rotacion del sol
        Quaternion rotacionSol = new Quaternion();
        rotacionSol.fromAngleAxis(FastMath.PI * timer.getTimeInSeconds() * 0.05f, Vector3f.UNIT_Y);
        sol.setLocalRotation(rotacionSol);
        
        // Orbita de mercurio
        float xMercurio = centro.x + (radioOrbita + 4f) * FastMath.cos(angle);
        float zMercurio = centro.z + (radioOrbita + 4f) * FastMath.sin(angle);
        float yMercurio = centro.y;
        Vector3f posicionMercurio = new Vector3f(xMercurio, yMercurio, zMercurio);
        mercurio.setLocalTranslation(posicionMercurio);

        // Rotacion de mercurio
        Quaternion rotacionMercurio = new Quaternion();
        rotacionMercurio.fromAngleAxis(FastMath.PI * time * 0.2f, Vector3f.UNIT_Y);
        mercurio.setLocalRotation(rotacionMercurio);

        // Orbita de venus
        float xVenus = centro.x + (radioOrbita + 8f) * FastMath.cos(angle * 0.8f);
        float zVenus = centro.z + (radioOrbita + 8f) * FastMath.sin(angle * 0.8f);
        Vector3f posicionVenus = new Vector3f(xVenus, yMercurio, zVenus);
        venus.setLocalTranslation(posicionVenus);

        // Rotacion de venus
        Quaternion rotacionVenus = new Quaternion();
        rotacionVenus.fromAngleAxis(FastMath.PI * time * 0.1f, Vector3f.UNIT_Y);
        venus.setLocalRotation(rotacionVenus);

        // Orbita de la Tierra
        float xTierra = centro.x + (radioOrbita + 14.5f) * FastMath.cos(angle * 0.6f);
        float zTierra = centro.z + (radioOrbita + 14.5f) * FastMath.sin(angle * 0.6f);
        Vector3f posicionTierra = new Vector3f(xTierra, yMercurio, zTierra);
        tierraNode.setLocalTranslation(posicionTierra);

        // Rotacion de la Tierra
        Quaternion rotacionTierra = new Quaternion();
        rotacionTierra.fromAngleAxis(FastMath.PI * time * 0.15f, Vector3f.UNIT_Y);
        tierraNode.setLocalRotation(rotacionTierra);
        
         // Orbita de la Luna
        float radioOrbitaLuna = 2f;
        float xLuna = centro.x + radioOrbitaLuna * FastMath.cos(timer.getTimeInSeconds() * 0.7f);
        float yLuna = posicionTierra.y;
        float zLuna = centro.z + radioOrbitaLuna * FastMath.sin(timer.getTimeInSeconds() * 0.7f);
        luna.setLocalTranslation(xLuna, yLuna, zLuna);

        // Orbita de Marte
        float xMarte = centro.x + (radioOrbita + 20f) * FastMath.cos(angle * 0.4f);
        float zMarte = centro.z + (radioOrbita + 20f) * FastMath.sin(angle * 0.4f);
        Vector3f posicionMarte = new Vector3f(xMarte, yMercurio, zMarte);
        marte.setLocalTranslation(posicionMarte);

        // Rotacion de Marte
        Quaternion rotacionMarte = new Quaternion();
        rotacionMarte.fromAngleAxis(FastMath.PI * time * 0.12f, Vector3f.UNIT_Y);
        marte.setLocalRotation(rotacionMarte);

        // Orbita de Júpiter
        float xJupiter = centro.x + (radioOrbita + 30f) * FastMath.cos(angle * 0.3f);
        float zJupiter = centro.z + (radioOrbita + 30f) * FastMath.sin(angle * 0.3f);
        Vector3f posicionJupiter = new Vector3f(xJupiter, yMercurio, zJupiter);
        jupiter.setLocalTranslation(posicionJupiter);

        // Rotacion de Júpiter
        Quaternion rotacionJupiter = new Quaternion();
        rotacionJupiter.fromAngleAxis(FastMath.PI * time * 0.1f, Vector3f.UNIT_Y);
        jupiter.setLocalRotation(rotacionJupiter);

        // Orbita de Saturno
        float xSaturno = centro.x + (radioOrbita + 50f) * FastMath.cos(angle * 0.25f);
        float zSaturno = centro.z + (radioOrbita + 50f) * FastMath.sin(angle * 0.25f);
        Vector3f posicionSaturno = new Vector3f(xSaturno, yMercurio, zSaturno);
        saturno.setLocalTranslation(posicionSaturno);

        // Rotacion de Saturno
        Quaternion rotacionSaturno = new Quaternion();
        rotacionSaturno.fromAngleAxis(FastMath.PI * time * 0.08f, Vector3f.UNIT_Y);
        saturno.setLocalRotation(rotacionSaturno);
        
        // Orbita de los anillos
        Quaternion rotacionAnillosSaturno = new Quaternion();
        rotacionAnillosSaturno.fromAngleAxis(FastMath.HALF_PI, Vector3f.UNIT_X); // Rotar 90 grados en el eje X
        anillosSaturno.setLocalRotation(rotacionAnillosSaturno);
        
        // Rotacion de los anillos
        anillosSaturno.setLocalTranslation(posicionSaturno);
        
        // Orbita de Urano
        float xUrano = centro.x + (radioOrbita + 75f) * FastMath.cos(angle * 0.2f);
        float zUrano = centro.z + (radioOrbita + 75f) * FastMath.sin(angle * 0.2f);
        Vector3f posicionUrano = new Vector3f(xUrano, yMercurio, zUrano);
        urano.setLocalTranslation(posicionUrano);

        // Rotacion de Urano
        Quaternion rotacionUrano = new Quaternion();
        rotacionUrano.fromAngleAxis(FastMath.PI * time * 0.05f, Vector3f.UNIT_Y);
        urano.setLocalRotation(rotacionUrano);

        // Orbita de Neptuno
        float xNeptuno = centro.x + (radioOrbita + 90f) * FastMath.cos(angle * 0.18f);
        float zNeptuno = centro.z + (radioOrbita + 90f) * FastMath.sin(angle * 0.18f);
        Vector3f posicionNeptuno = new Vector3f(xNeptuno, yMercurio, zNeptuno);
        neptuno.setLocalTranslation(posicionNeptuno);

        // Rotacion de Neptuno
        Quaternion rotacionNeptuno = new Quaternion();
        rotacionNeptuno.fromAngleAxis(FastMath.PI * time * 0.04f, Vector3f.UNIT_Y);
        neptuno.setLocalRotation(rotacionNeptuno);

        // Orbita de Plutón
        float xPluton = centro.x + (radioOrbita + 110f) * FastMath.cos(angle * 0.15f);
        float zPluton = centro.z + (radioOrbita + 110f) * FastMath.sin(angle * 0.15f);
        Vector3f posicionPluton = new Vector3f(xPluton, yMercurio, zPluton);
        pluton.setLocalTranslation(posicionPluton);

        // Rotacion de Plutón
        Quaternion rotacionPluton = new Quaternion();
        rotacionPluton.fromAngleAxis(FastMath.PI * time * 0.03f, Vector3f.UNIT_Y);
        pluton.setLocalRotation(rotacionPluton);
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
