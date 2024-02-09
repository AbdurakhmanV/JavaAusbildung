package Camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainCamera {
    static int amountOfCameras = 0;
    static int amountOfLenses = 0;

    public static void main(String[] args) {
        List<Camera> cameraList = new ArrayList();
        List<Lens> lensList = new ArrayList();
        Scanner scan = new Scanner(System.in);

        int chooseProgram;
        do {
            System.out.println("1. Kamera erstellen\n2. Objektiv erstellen\n3. Kamera-Liste\n4. Objectiv-Liste\n5. Objectiv zu Kamera hinzufuegen oder wechseln");
            chooseProgram = scan.nextInt();
            if (chooseProgram == 1) {
                Camera camera = createCamera();
                System.out.println(camera);
                System.out.println();
                System.out.println("0.Exit\n1.zu Kamera Liste hinzufuegen");
                chooseProgram = scan.nextInt();
                if (chooseProgram == 1) {
                    cameraList = addToCameraList(cameraList, camera);
                }

                if (chooseProgram == 0) {
                    chooseProgram = -1;
                }
            } else if (chooseProgram == 2) {
                Lens lens = createLens();
                if (lens != null) {
                    System.out.println(lens);
                    System.out.println("0.Exit\n1.zu Objektiv Liste hinzufuegen");
                    chooseProgram = scan.nextInt();
                    if (chooseProgram == 1) {
                        lensList = addToLensList(lensList, lens);
                    }

                    if (chooseProgram == 0) {
                        chooseProgram = -1;
                    }
                }
            } else if (chooseProgram == 3) {
                showCameraList(cameraList);
            } else if (chooseProgram == 4) {
                showLensList(lensList);
            } else if (chooseProgram == 5) {
                showCameraList(cameraList);
                changeLensOfCamera(cameraList, lensList);
            }
        } while(chooseProgram != 0);

    }

    private static Camera createCamera() {
        Scanner scan = new Scanner(System.in);
        Lens lens = new Lens();
        System.out.println("Schreibe eine id fuer die Kamera rein.");
        String id = scan.next();
        System.out.println("Schreibe die Marke rein.");
        String brand = scan.next();
        System.out.println("Schreib die MegaPixel Anzahl ein.");
        double megaPixel = scan.nextDouble();
        System.out.println("Gib die Groesse des Displayes an.");
        double displaySize = scan.nextDouble();
        System.out.println("Farbe oder Schwarz-Weiss?");
        boolean color = false;

        int chooseProgram;
        do {
            System.out.println("1. Farbe\n2. Schwarz-Weiss");
            chooseProgram = scan.nextInt();
            if (chooseProgram > 0 && chooseProgram <= 2) {
                color = chooseProgram == 1;
            }
        } while(chooseProgram < 1 || chooseProgram > 2);

        return new Camera(id, brand, megaPixel, displaySize, color, lens);
    }

    private static Lens createLens() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Schreibe eine id fuer das Objektiv rein.");
        String id = scan.next();
        System.out.println("Gib die minimale Brennweite an.");
        double minFocalLength = scan.nextDouble();
        System.out.println("Gib die maximale Brennweite an.");
        double maxFocalLength = scan.nextDouble();

        try {
            return new Lens(id, minFocalLength, maxFocalLength);
        } catch (Exception var7) {
            System.out.println(var7.getMessage());
            var7.printStackTrace();
            return null;
        }
    }

    private static List<Camera> addToCameraList(List<Camera> cameraList, Camera camera) {
        if (cameraList == null) {
            cameraList = new ArrayList();
        }

        (cameraList).add(camera);
        ++amountOfCameras;
        return cameraList;
    }

    private static List<Lens> addToLensList(List<Lens> lensList, Lens lens) {
        if (lensList == null) {
            lensList = new ArrayList();
        }

        lensList.add(lens);
        ++amountOfLenses;
        return lensList;
    }

    private static void showCameraList(List<Camera> cameraList) {
        if (cameraList == null) {
            System.out.println("Es gibt keine Kamera in der Kamera-Liste");
        } else {
            System.out.println("Es gibt " + cameraList.size() + " Kameras zur Auswahl.");
            System.out.println();
            Iterator var1 = cameraList.iterator();

            while(var1.hasNext()) {
                Camera e03Camera = (Camera)var1.next();
                System.out.println(e03Camera.toString());
                System.out.println();
            }
        }

    }

    private static void showLensList(List<Lens> lensList) {
        if (lensList == null) {
            System.out.println("Es gibt kein Objectiv in der Kamera-Liste");
        } else {
            System.out.println("Es gibt " + lensList.size() + " Objektive zur Auswahl.");
            System.out.println();
            Iterator lensListIterator = lensList.iterator();

            while(lensListIterator.hasNext()) {
                Lens lens = (Lens)lensListIterator.next();
                System.out.println(lens.toString());
                System.out.println();
            }
        }

    }

    private static void changeLensOfCamera(List<Camera> cameraList, List<Lens> lensList) {
        Scanner scan = new Scanner(System.in);
        Camera camera = null;
        Lens lens = null;
        System.out.println("Waehle eine Kamera aus indem du ihr id eingibst.");
        String idCamera = scan.next();

        do {
            int chooseProgram;
            for(chooseProgram = 0; chooseProgram < cameraList.size(); ++chooseProgram) {
                if (idCamera.equals((cameraList.get(chooseProgram)).getId())) {
                    camera = cameraList.get(chooseProgram);
                    chooseProgram = cameraList.size() - 1;
                } else if (chooseProgram == cameraList.size() - 1 && !((cameraList.get(cameraList.size() - 1)).getId().equals(idCamera))){
                    System.out.println("Diese id fuer ein Objektiv existiert nicht.");
                    camera = null;
                }
            }

            if (camera != null) {
                System.out.println(camera);

                do {
                    System.out.println("0.Exit\n1.Objektiv wechseln");
                    chooseProgram = scan.nextInt();
                    if (chooseProgram == 1) {
                        showLensList(lensList);
                        System.out.println("Waehle ein Objektiv aus indem du dessen id eingibst");
                        String idLens = scan.next();

                        for(int i = 0; i < lensList.size(); ++i) {
                            if (idLens.equals((lensList.get(i)).getId())) {
                                lens = lensList.get(i);
                                camera.setLens(lens);
                                i = lensList.size() - 1;
                            } else if (i == lensList.size() - 1 && !(cameraList.get(cameraList.size() - 1)).getId().equals(idCamera)) {
                                System.out.println("Diese id fuer ein Objektiv existiert nicht.");
                                lens = null;
                            }
                        }
                    }
                } while(chooseProgram != 1 || lens == null);
            }
        } while(camera == null);

    }
}
