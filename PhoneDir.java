import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class phonedir {
	
	static int currentIndex = -1;

	//-----------------------------------------------------------------------------
	//Inserts a record newRec into List list
	//Precondition: newRec must have a last name with alphabetical letters 
	//Postcondition: the method either puts newRec into the first spot in the empty list
	//or, if the list is populated, searches for its alphabetical place and is inserted there
	//-----------------------------------------------------------------------------
	public static void alphaSort(List<Record> list, Record newRec){
		int index = 0;

		while(index < list.size()){
			if(list.isEmpty()){
				list.add(newRec);
				currentIndex = index;
				return;
			}

			else if(list.get(index).lname.compareTo(newRec.lname)> 0){	//if true, lname in the list is bigger
				list.add(index, newRec);
				currentIndex = index;
				return;
			}
			else{
				index++;
			}
		}

		currentIndex = index;
		list.add(newRec);

	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		List<Record> list = new LinkedList<Record>();

		//Invariant: LinkedList is always sorted alphabetically by last name
		while (true) {
			System.out.println("\ta: Show all records");
			System.out.println("\td: Delete the current record");
			System.out.println("\tf: Change the first name in the current record");
			System.out.println("\tl: Change the last name in the current record");
			System.out.println("\tn: Add a new record");
			System.out.println("\tp: Change the phone number in the current record");
			System.out.println("\tq: Quit");
			System.out.println("\ts: Select a record from the record list to become the current record");
			System.out.println();
			System.out.println("Enter a command from the list above (q to quit):");

			char operator = input.nextLine().charAt(0);

			switch (operator) {
			
//-----------------------------------------------------------------------------
//Prints out phone directory with records.  Formatted with 3 columns: first name, last name and phone number
//postcondition: Returns formatted list of records split up in 3 columns
//loops through the list and adds all the records to string records and formats accordingly
//using String.format.  Then prints out the formatted string.
//-----------------------------------------------------------------------------
			case 'a': 
				String records = "";
				String title = "First Name \t Last Name \t Phone Number\n" ;
				records = title +"------------\t------------\t------------\n";
				for(Record r : list)
					records += String.format("%s\t\t%s\t\t%s\n",r.fname, r.lname, r.num);
				System.out.println(records);

				break;
//-----------------------------------------------------------------------------
//Deletes Current Record
//postcondition: If list is empty, prints "There is nothing in the list", if no current record, print "No current
//record, otherwise, removes record at currentIndex and sets int currentIndex to -1
//-----------------------------------------------------------------------------
			case 'd': if(list.isEmpty()){
				System.out.println("There is nothing in the list");
			}
			else if(currentIndex == -1){
				System.out.println("No current record");
			}
			else{
				list.remove(currentIndex);
				currentIndex = -1;
			}
			break;
//-----------------------------------------------------------------------------
//Changes first name in current index
//precondition: user is prompted to enter new first name
//postcondition: if list is empty, prints "There is nothing in the list", if no current record, print "No current
//record, otherwise, changes first name in current record and prints out message with the current record
//-----------------------------------------------------------------------------
			case 'f':
			{
				if(list.isEmpty()){
					System.out.println("There is nothing in the list");
				}
				else if(currentIndex == -1){
					System.out.println("No current record");
				}
				else{

					System.out.println("Enter New First Name: ");
					String first = input.nextLine();

					list.get(currentIndex).setFname(first);

					System.out.println("The current record is: " + list.get(currentIndex));
				}
			}
			break;
//-----------------------------------------------------------------------------
//Changes last name in current record
//precondition: user is prompted to enter new last name
//postcondition: if list is empty, prints "There is nothing in the list", if no current record, print "No current
//record, otherwise, changes last name in current record by storing record as temp, deleting it, and adding temp
//to the list using alphaSort and prints out message with the current record
//-----------------------------------------------------------------------------
			case 'l':
			{
				if(list.isEmpty()){
					System.out.println("There is nothing in the list");
				}
				else if(currentIndex == -1){
					System.out.println("No current record");
				}
				else{

					System.out.println("Enter New Last Name: ");
					String last = input.nextLine();


					Record temp = list.get(currentIndex);

					list.remove(currentIndex);

					temp.setLname(last);

					alphaSort(list, temp);

					System.out.println("The current record is: " + list.get(currentIndex));
				}
			}
			break;
//-----------------------------------------------------------------------------
//User is prompted to create a new record to be added to directory
//precondition: user is prompted to enter a first and last name and phone number
//postcondition: new record is created and added to list with alphaSort method and user receives 
//message indicating that this record is now the current record
//-----------------------------------------------------------------------------
			case 'n': 
			{
				System.out.println("Enter First Name: ");
				String first = input.nextLine();

				System.out.println("Enter Last Name: ");
				String last = input.nextLine();

				System.out.println("Enter Phone Number: ");
				String num = input.nextLine();

				Record record = new Record(first, last, num);

				alphaSort(list, record);

				System.out.println("The current record is: " + record);
			}
			break;
//-----------------------------------------------------------------------------
//Changes the phone number in the current record
//precondition: user is prompted to enter a new phone number
//postcondition: if list is empty, prints "There is nothing in the list", if no current record, print "No current
//record, otherwise, changes the number in the current record and prints the current record with this new 
//phone number
//-----------------------------------------------------------------------------
			case 'p':
				if(list.isEmpty()){
					System.out.println("There is nothing in the list");
				}
				else if(currentIndex == -1){
					System.out.println("No current record");
				}
				else{
					System.out.println("Enter A New Phone Number: ");

					String num = input.nextLine();

					list.get(currentIndex).setNum(num);

					System.out.println("The current record is: " + list.get(currentIndex));
				}
				break;
//-----------------------------------------------------------------------------
//Terminates program
//postcondition: Prompts don't reappear and program terminates
//-----------------------------------------------------------------------------
			case 'q':
				System.exit(0);
				break;
//-----------------------------------------------------------------------------
//Lets user select a record in the list to become the new current record
//precondition: user is prompted to enter a first and last name
//postcondition: tests if list is empty and prints "there is nothing in the list" if so.
//Otherwise, loops through the list looking for matching record to what was inputted.  If found, it sets that record
//as the currentIndex and prints it out as the current record.  Otherwise, it prints "no matching record found"
//-----------------------------------------------------------------------------
			case 's':
				if(list.isEmpty()){
					System.out.println("There is nothing in the list");
				}
				else{
					System.out.println("Enter First Name: ");
					String fName = input.nextLine();
					System.out.println("Enter Last Name: ");
					String lName = input.nextLine();

					boolean found = false;

					Record dummy = new Record(fName, lName, "555-555-5555");
					for(int i = 0; i < list.size(); i++){
						if(dummy.equals(list.get(i))){
							currentIndex = i;
							found = true;
							System.out.println("The current record is: " + list.get(currentIndex));
							break;
						}
					}
					if(!found){
						System.out.println("No Matching Record Found");
					}
				}
				break;

			//if anything other than what is offered is entered by user
			default:
				System.out.println("Illegal Command");
			}
		}
	}
}
