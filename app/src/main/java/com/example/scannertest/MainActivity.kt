package com.example.scannertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.zbt.jpos.ScannerService
import com.zbt.jpos.ZbtJposServiceInstanceFactory
import com.zbt.jpos.ZbtJposUsb
import jpos.BaseControl
import jpos.Scanner
import jpos.config.JposEntry
import jpos.events.*
import jpos.services.EventCallbacks
import jpos.services.ScannerService18
import jpos.util.JposPropertiesConst

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        System.setProperty(
            JposPropertiesConst.JPOS_POPULATOR_FILE_PROP_NAME,
            "jpos.xml"
        )

//        System.getenv("C:\\Users\\apushkarev\\AndroidStudioProjects\\ScannerTest\\app\\src\\jpos.xml")
//
        System.setProperty("jpos.loader.serviceManagerClass", "jpos.loader.simple.SimpleServiceManager");
     //   System.setProperty("jpos.config.populatorFile", "jposxml.cfg");
        System.setProperty("jpos.util.tracing.TurnOnNamedTracers", "JposServiceLoader,SimpleEntryRegistry,SimpleRegPopulator,XercesRegPopulator");
        System.setProperty("jpos.util.tracing.TurnOnAllNamedTracers", "ON");
        System.setProperty("jpos.config.regPopulatorClass", "jpos.config.simple.xml.SimpleXmlRegPopulator");


        val scannerServiceInstance = ScannerService()
        try {
            scannerServiceInstance.addStatusUpdateListener {
                Log.e("andrey", "onCreate: it.status = ${it.status}")
            }

            scannerServiceInstance.open("defaultScanner2D", object : EventCallbacks {
                override fun fireDataEvent(p0: DataEvent?) {
                    Log.e("andrey", "fireDataEvent: ")
                }

                override fun fireDirectIOEvent(p0: DirectIOEvent?) {
                    Log.e("andrey", "fireDirectIOEvent: ")
                }

                override fun fireErrorEvent(p0: ErrorEvent?) {
                    Log.e("andrey", "fireErrorEvent: ")
                }

                override fun fireOutputCompleteEvent(p0: OutputCompleteEvent?) {
                    Log.e("andrey", "fireOutputCompleteEvent: ")
                }

                override fun fireStatusUpdateEvent(p0: StatusUpdateEvent?) {
                    Log.e("andrey", "fireStatusUpdateEvent: ")
                }

                override fun getEventSource(): BaseControl {
                    TODO("Not yet implemented")
                }
            })

            scannerServiceInstance.claim(1000)
        } catch (e: Exception){
            Log.e(
                "andrey",
                "onCreate: Exception = ${e.message}",
            )
        }


        Log.e(
            "andrey",
            "onCreate: scannerServiceInstance.phyinterface = ${scannerServiceInstance.phyinterface}"
        )

        Log.e(
            "andrey",
            "onCreate: scannerServiceInstance.physicalDeviceName = ${scannerServiceInstance.physicalDeviceName}",
        )

//        try {
//            val sc = Scanner()
//            sc.open("defaultScanner2D")
//            sc.claim(1000)
//        } catch (e: Exception){
//            Log.e("andrey", "onCreate: ${e.message}", )
//        }


//        val scanner = ZbtJposUsb(
//            scannerServiceInstance
//        )

//        try{
//            scanner.run()
//            scanner.GetUsbData(scanData())
//        } catch ( e: Exception) {
//            Log.e("andrey", "onCreate: e = $e", )
//        }

        //TODO getUsbData
//        val jposServiceInstance = ZbtJposServiceInstanceFactory().createInstance("defaultScanner2D", JposEntry. )

    }

    private fun scanData(): ByteArray? {
        Log.e("andrey", "scanData: ")
        return null
    }

}