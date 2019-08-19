# RoboSim Metamodel [![wercker status](https://app.wercker.com/status/2a9fb242e76e338497d8dc86f673bffd/s/master "wercker status")](https://app.wercker.com/project/byKey/2a9fb242e76e338497d8dc86f673bffd)
This repository contains the eclipse plugins that implement that metamodel of RoboSim.

### Development platform requirements ###

* Eclipse 2019-06
* Xtext 2.18.0 can be found in the standard repository in http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/ but the option "Show only the latest versions of available software" needs to be unchecked. 
* Maven
* Git

### Build (maven) ###

        1. mvn clean install

### Build (eclipse) ###

        1. Right click circus.robocalc.robosim/model/robosim.genmodel 
            1. click 'reload'
            2. select 'Ecore model' and click 'Next'
            3. click 'Next'
            4. click 'Finish'
        2. In the open RoboChart.genmodel      
            1. right click the item 'RoboSim' and select 'Generate Model Code'
            2. right click the item 'RoboSim' and select 'Generate Edit Code'
            3. right click the item 'RoboSim' and select 'Generate Editor Code'
            
(The genmodel file refers to the RoboChart.genmodel file via a URL. It seems this is resolved to a local
path when the file is open in eclipse. Do not commit this change.)

### Run (eclipse) ###

        1. Right click circus.robocalc.robochart.parent
            1. select 'Run As'
            2. double click 'Eclipse Application'
        2. Select the new configuration
            1. click 'Run'
            
### Protocol for updating the tool ###

Whenever updating the tool, follow these steps:

        1. Run Maven with Tests
        2. Change the language reference manual
        3. Change the tool manual

If changes to documentations are not possible immediately, create issues indicating exactly what needs to be done.