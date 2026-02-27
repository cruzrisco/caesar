<p>
<img width="166" height="67" alt="caesar1" src="https://github.com/user-attachments/assets/116ab7ce-02a9-4965-a41c-3ba2dff671ed" />
</p>

The purpose of the CÆSAR tool is to enable the quantification of the impact of changes in experimental validity, making it possible to visually depict the evolution of the family of experiments.
The tool is intended for researchers, students, and professionals working in the area of empirical software engineering.


For the development of Caesar, the Model–View–Controller (MVC) architectural pattern was applied. Grails was used, a web application framework built on top of the Java Virtual Machine that uses the Groovy programming language.
CÆSAR is deployed for demonstration http://caesar.us.es/

The steps to follow to work with CÆSAR are:

1.- Create an experiment, registering its description, as well as, among other details, the place where it is conducted, the date, and its objectives.


2.- Create a replication, specifying the date, whether it is internal or external, and its purpose (to confirm results, to generalize results, or to overcome limitations of previous studies). You must also indicate the base experiment, which may be an original experiment or a previous replication.


3.- Record the changes in that replication with respect to the base experiment. For each change, in addition to its description and the dimension it affects, you specify how it impacts internal, external, construct, and conclusion validity by assigning a value in the range from +3 to –3.
When the change increases validity, it is rated with a positive value (+1, +2, or +3), where +3 corresponds to a very significant improvement in a specific type of validity. When the change decreases validity, it is rated with a negative value (–1, –2, or –3). A value of 0 indicates that the change does not affect that type of validity.


4.- Based on this quantification, it is possible to graphically represent how validity varies—for each type of threat—depending on the changes introduced in the replications that make up a family of experiments.


5.- It is possible to visualize an original experiment together with its replications, examine each of their changes, and graphically represent the evolution of experimental validity throughout the entire family of experiments.
