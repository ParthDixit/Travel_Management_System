package repositories;

import models.travelPackage.Package;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class PackageRepository {

    private static  PackageRepository packageRepository;
    private final Map<String, Package> packageMap;

    private PackageRepository(){
        packageMap = new HashMap<>();
    }

    public static PackageRepository getInstance(){

        if(packageRepository == null)
        {
            synchronized (PackageRepository.class)
            {
                if (packageRepository == null)
                    packageRepository = new PackageRepository();
            }
        }

        return packageRepository;
    }

    public Package save(@NotNull Package packageObj){

        if(packageMap.containsKey(packageObj.getName()))
            return null;
            //throw new RuntimeException("Package already registered.");

        packageMap.put(packageObj.getName(), packageObj);

        return packageObj;
    }

    public Package findPackageByName(String name){

        if(packageMap.containsKey(name))
            return packageMap.get(name);

        return null;
    }

}
