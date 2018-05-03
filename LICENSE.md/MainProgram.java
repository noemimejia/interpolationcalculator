import java.util.*;
import java.io.*;
class MainProgram
{
	public static String getString() throws Exception
	{
		InputStreamReader sr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (sr);
		String input = br.readLine();
		return input;
	}
	public static int getNumber() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		return number;
	}
	public static float getDecimal() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		float number = sc.nextFloat();
		return number;
	}
	public static float getUnknown() throws Exception
	{
		int unknownerror;
		float unknown; // x value 
		do
		{
			unknownerror = 0;
			try
			{
				System.out.println("");
				for (int i = 0; i < 80; i++)
				{
					System.out.printf("=");
				}
				System.out.printf("\nEnter the x value for which f(x) is unknown: ");
				unknown = getDecimal();
			}
			catch (InputMismatchException x)
			{
				unknown = 0;
				unknownerror++;
				System.out.println("Error! Please enter a valid number.");
			}
		} while (unknownerror > 0 && unknown == 0);
		return unknown;
	}
	public static String mainMenu() throws Exception
	{
		String input;
		do
		{
			System.out.println("");
			for (int i = 0; i < 80; i++)
			{
				System.out.printf("=");
			}
			System.out.printf("\nInterpolation Calculator! \nDo you want to: \n[A] Learn more about numerical differentiation and interpolation \n[B] Start interpolating! \n[C] Exit \nAnswer: ");
			input = getString();
		} while (input.equalsIgnoreCase("a") == false && input.equalsIgnoreCase("b") == false && input.equalsIgnoreCase("c") == false);
		return input;	
	}
	public static String changeValues() throws Exception
	{
		String answer;
		do
		{
			System.out.println("");
			for (int i = 0; i < 80; i++)
			{
				System.out.printf("=");
			}
			System.out.printf("\nDo you want to: \n[A] Change any entered value \n[B] Proceed to interpolate \nAnswer: ");
			answer = getString();
			answer = answer.toLowerCase();
		} while ((answer.isEmpty() == true) || ((answer.equals("a") == false) && (answer.equals("b") == false)));
		return answer.toLowerCase();
	}
	public static String infoMenu() throws Exception
	{
		String answer;
		int error;
		do
		{
			error = 0;
			System.out.println("");
			for (int i = 0; i < 80; i++)
			{
				System.out.printf("=");
			}
			System.out.printf("\nDo you want to know more about: \n[A] Interpolation \n[B] Newton's Forward and Backward Differences \n[C] Gauss' Forward, Backward, and Central Differences \n[D] Cubic Spline \n[E] Lagrange Interpolation Method \n[F] Newton's Divided Differences \n[G] Return to the main menu \n[H] Exit \nAnswer: ");
			answer = getString();
			switch (answer.toLowerCase())
			{
				case "a":
				case "b":
				case "c":
				case "d":
				case "e":
				case "f":
					return answer;
				case "g":
					return "STARTOVER";
				case "h":
					return "EXIT";
				default:
					error++;
					break;			
			}
		} while (error > 0);
		return answer;
	}
	public static int getSize() throws Exception
	{
		int knownvalues;
		int sizeerror;
		do
		{
			do
			{
				sizeerror = 0;
				try
				{
					System.out.println("Enter the array size: ");
					knownvalues = getNumber(); //requires integer
				}
				catch (InputMismatchException s)
				{
					knownvalues = 0;
					sizeerror++;
					System.out.println("Error! Please enter a valid number.");
				}	
			} while (sizeerror > 0);
		} while (knownvalues <= 1);
		return knownvalues;
	}
	public static float[][] encodeValues(int size) throws Exception
	{	
		float[][] xy;
		float number;
		int rows, columns, error;
		xy = new float[size][2];
		for (rows = 0; rows < size; rows++)
		{
			for (columns = 0; columns < 2; columns++)
			{
				if (columns == 0)
				{
					do
					{
						error = 0;
						System.out.printf((rows+1) + ". Enter the value for x: ");
						try
						{
							number = getDecimal(); //requires float
						}
						catch (InputMismatchException e)
						{
							number = 0;
							System.out.println("Error! Please enter a valid number.");
							error++;
						}
					} while (error > 0);
					xy[rows][columns] = number;
				} // closes if for (columns == 0)	
				else
				{
					do
					{
						error = 0;
						System.out.printf("Enter the value for y: ");
						try
						{
							number = getDecimal(); //requires float
						}
						catch (InputMismatchException e)
						{
							number = 0;
							System.out.println("Error! Please enter a valid number.");
							error++;
						}
					} while (error > 0);
					xy[rows][columns] = number;
				} //closes else
			} //closes column loop
		} //closes row loop
		return xy;
	}
	public static float[][] wantToChangeValues(float[][] xy, int size) throws Exception
	{
		int newvalue, rownumber;
		String choice;
		do
		{
			System.out.printf("\nDo you want to change the \n[A] x value \n[B] y value \nAnswer: ");
			choice = getString();
		} while ((choice.isEmpty() == true) || ((choice.equalsIgnoreCase("a") == false) && (choice.equalsIgnoreCase("b") == false)));
		int rowerror;
		do
		{
			rowerror = 0;
			System.out.printf("\nEnter the row number of the value you want to change: ");
			try
			{
				rownumber = getNumber();
				rownumber = rownumber - 1;
				if (rownumber < size && rownumber >= 0) //if row entered is within the range of the array
				{
					if (choice.equalsIgnoreCase("a") == true) //x value
					{
						System.out.printf("\nEnter the new x value: ");
						float newnumber = getDecimal();
						xy[rownumber][0] = newnumber;
					}
					else if (choice.equalsIgnoreCase("b") == true) //y
					{
						System.out.printf("\nEnter the new y value: ");
						float newnumber = getDecimal();
						xy[rownumber][1] = newnumber;
					} // closes else if 
				} //closes if condition
				else //row number entered exceeds the number of known values
				{
					System.out.println("\nError! The row number entered cannot be found.");
					rowerror++;
				} //closes else condition
			} //closes try catch
			catch (InputMismatchException e)
			{
				System.out.println("Error! Please enter a valid number.");
				rowerror++;
			} //closes try catch
		} while (rowerror > 0);
		return xy;
	}
	public static String askForMethod(boolean alreadyAnswered, boolean isEqual) throws Exception
	{
		String answer, chosenMethod = "";
		int error;
		boolean startOver = false;
		boolean exit = false;
		do
		{
			error = 0;
			if (alreadyAnswered == true)
			{
				if (isEqual == true)
				{
					do
					{
						System.out.println("");
						for (int i = 0; i < 80; i++)
						{
							System.out.printf("=");
						}
						error = 0;
						System.out.printf("\nDo you want to: \n[A] Repeat the interpolation with a different method \n[B] Start over \n[C] Exit \nAnswer: ");
						answer = getString();
						switch (answer.toLowerCase())
						{
							case "a": //choose another method
								int methoderror;
								do
								{
									methoderror = 0;
									System.out.printf("\nWhat method do you want to use? \n[A] Newton's Forward Method \n[B] Newton's Backward Method \n[C] Gauss' Forward Method \n[D] Gauss' Backward Method \n[E] Cubic Spline \nAnswer: ");
									chosenMethod = getString();
									switch (chosenMethod.toLowerCase())
									{
										case "a":
										case "b":
										case "c":
										case "d":
										case "e":
											return chosenMethod;
										default:
											methoderror++;
									}
								} while (methoderror > 0);
							case "b": //start over
								startOver = true;
								return "STARTOVER";
							case "c": //exit program
								exit = true;
								return "EXIT";
							default: 
								error++;
								break;
						} // closes switch
					}while (error > 0 && startOver == false && exit == false);
				} //closes if condition (isEqual)
				else //if isEqual == false
				{
					do
					{
						System.out.println("");
						for (int i = 0; i < 80; i++)
						{
							System.out.printf("=");
						}
						error = 0;
						System.out.printf("\nDo you want to: \n[A] Repeat the interpolation with a different method \n[B] Start over \n[C] Exit \nAnswer: ");
						answer = getString();
						switch (answer.toLowerCase())
						{
							case "a": //choose another method
								int methoderror;
								do
								{
									methoderror = 0;
									System.out.printf("\nWhat method do you want to use? \n[A] Lagrange's Method \n[B] Divided Differences Method \nAnswer: ");
									chosenMethod = getString();
									switch (chosenMethod.toLowerCase())
									{
										case "a":
											return "f";
										case "b":
											return "g";
										default:
											methoderror++;
									}
								} while (methoderror > 0);
							case "b": //start over
								startOver = true;
								return "STARTOVER";
							case "c": //exit program
								exit = true;
								return "EXIT";
							default: 
								error++;
								break;
						} // closes switch
					}while (error > 0 && startOver == false && exit == false);
				} //closes else (not equal)
			} // closes if condition (alreadyAnswer == true)
			else //not yet answered
			{
				if (isEqual == true)
				{
					int methoderror;
					do
					{
						methoderror = 0;
						System.out.println("");
						for (int i = 0; i < 80; i++)
						{
							System.out.printf("=");
						}
						System.out.printf("\nWhat method do you want to use? \n[A] Newton's Forward Method \n[B] Newton's Backward Method \n[C] Gauss' Forward Method \n[D] Gauss' Backward Method \n[E] Cubic Spline \nAnswer: ");												
						chosenMethod = getString();
						switch (chosenMethod.toLowerCase())
						{
							case "a":
							case "b":
							case "c":
							case "d":
							case "e":
								return chosenMethod;
							default:
								methoderror++;
						}
					} while (methoderror > 0);
				} //closes if (isEqual == true)
				else //not equal
				{
					int methoderror;
					do
					{
						methoderror = 0;
						System.out.printf("\nWhat method do you want to use? \n[A] Lagrange's Method \n[B] Divided Differences \nAnswer: ");																		chosenMethod = getString();
						switch (chosenMethod.toLowerCase())
						{
							case "a":
								return "f";
							case "b":
								return "g";
							default:
								methoderror++;
						}
					} while (methoderror > 0);
				} //closes else (isEqual == false)
			} //closes else condition
		} while (error > 0);
		return chosenMethod;
	}
	public static void main (String[] args) throws Exception
	{
		Interpolate interpolation1 = new Interpolate();
		MoreInfo info1 = new MoreInfo();
		int errorcounter;
		boolean startOver;
		boolean exit;
		do
		{
			startOver = false;
			exit = false;
			errorcounter = 0;
			String input = mainMenu();
			input = input.toLowerCase();
			switch (input)
			{
				case "a":
					do
					{
						String chosenMethod = infoMenu();
						switch (chosenMethod.toLowerCase())
						{
							case "a":
								System.out.printf(info1.getInterpolation());
								break;
							case "b":
								System.out.printf(info1.getNewton());
								break;
							case "c":
								System.out.printf(info1.getGauss());
								break;
							case "d":
								System.out.printf(info1.getCubic());
								break;
							case "e":
								System.out.printf(info1.getLagrange());
								break;
							case "f":
								System.out.printf(info1.getDivided());
								break;
							case "startover":
								startOver = true;
								break;
							case "exit":
								exit = true;
								break;
						}
					} while (startOver == false && exit == false);
					break;
				case "b": 
					int size = getSize();
					float[][] table = encodeValues(size);
					interpolation1.setTable(table, size);
					interpolation1.displayTable();
					String answer;
					float[][] newtable;
					boolean validTable = false;
					boolean validAnswer = false;
					do
					{
						validTable = interpolation1.checkTable();
						answer = changeValues();
						if (validTable == false)
							System.out.println("\nError! There is something wrong with your table. Please enter distinct values for x.");
						if (answer.equals("a") == true)
						{
							validAnswer = true;
							newtable = wantToChangeValues(table, size);
							interpolation1.setTable(newtable, size);
							interpolation1.displayTable();
						}
						else if (answer.equals("b") == true)
							validAnswer = true;
					} while ((answer.equals("a") == true || validAnswer == false) || validTable == false);
					boolean validUnknown;
					do
					{
						if (interpolation1.checkUnknown(getUnknown()) == true)
							validUnknown = true;
						else
							validUnknown = false;
					} while (validUnknown == false);
/*if intervals are equal/unequal*/	boolean isEqual = interpolation1.checkInterval();
					boolean alreadyAnswered = false;
					int repeatCounter = 0;
					String chosenMethod;
					int methoderror;
					do
					{
						methoderror = 0;
						if (repeatCounter == 0)
							alreadyAnswered = false;
						else
							alreadyAnswered = true;
						chosenMethod = askForMethod(alreadyAnswered, isEqual);
						if (alreadyAnswered == true)
							interpolation1.resetValues();
						switch (chosenMethod)
						{
							case "a": //newtonforward
								if (size >= 3)
									interpolation1.NewtonForward();
								else
								{
									methoderror++;
									System.out.println("\nError! The known values should be 3 or more. Please try another method.");	
								}
								break;
							case "b": //newtonbackward
								if (size >= 3)
									interpolation1.NewtonBackward();
								else
								{
									methoderror++;
									System.out.println("\nError! The known values should be more than 4. Please try another method.");	
								}
								break;
							case "c": //gaussforward
								if (size > 4)
									interpolation1.GaussForward();
								else
								{
									methoderror++;
									System.out.println("\nError! The known values should be more than 4. Please try another method.");	
								}
								break;
							case "d": //gaussbackward
								if (size > 4)
									interpolation1.GaussBackward();
								else
								{
									methoderror++;
									System.out.println("\nError! The known values should be 4 or more. Please try another method.");	
								}
								break;
							case "e": //cubic
								interpolation1.CubicSpline();
								break;
							case "f": //lagrange
								interpolation1.LagrangeMethod();
								break;
							case "g": //divided
								if (size >= 4)
									interpolation1.DividedDifferences();
								else
								{
									methoderror++;
									System.out.println("\nError! The known values should be 4 or more. Please try another method.");	
								}
								break;
							case "STARTOVER":
								startOver = true;
								break;
							case "EXIT":
								System.out.println("\nExiting...");
								exit = true;
								break;
						}
						if (methoderror == 0 && startOver == false && exit == false)
						{
								interpolation1.printAnswer();
								repeatCounter++;
						}
					} while (alreadyAnswered = true && startOver == false && exit == false);
					break;
				case "c":
					exit = true;
					break;
				default:
					errorcounter++;
					System.out.println("Error! Please enter a valid input.");
					break;
			} //closes switch
		} while ((errorcounter > 0 || startOver == true) && exit == false);
	} //closes method
} //closes class