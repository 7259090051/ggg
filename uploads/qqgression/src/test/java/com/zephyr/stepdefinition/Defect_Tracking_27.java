package com.zephyr.stepdefinition;

import org.openqa.selenium.InvalidElementStateException;
import org.testng.asserts.SoftAssert;

import com.zephyr.common.LaunchBrowser;
import com.zephyr.generic.Excel_Lib;
import com.zephyr.generic.Property_Lib;
import com.zephyr.reusablemethods.BasePage;
import com.zephyr.reusablemethods.CreateTestCasePage;
import com.zephyr.reusablemethods.DashBoardPage;
import com.zephyr.reusablemethods.DefectTracking;
import com.zephyr.reusablemethods.ExecutionPage;
import com.zephyr.reusablemethods.ExportPage;
import com.zephyr.reusablemethods.LoginPage;
import com.zephyr.reusablemethods.ProjectPage;
import com.zephyr.reusablemethods.ReleasePage;
import com.zephyr.reusablemethods.RequirementsPage;
import com.zephyr.reusablemethods.TestPlanningPage;
import com.zephyr.reusablemethods.TestRepositoryPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Defect_Tracking_27 extends LaunchBrowser{
	BasePage bp;
	ProjectPage pp;
	ReleasePage rp;
	TestRepositoryPage tr;
	TestPlanningPage tp;
	DashBoardPage dp;
	ExecutionPage ep;
	CreateTestCasePage ctc;
	LaunchBrowser lb;
	LoginPage lp;
	ExportPage ex;
	RequirementsPage req;
	DefectTracking dt;
    boolean[] actual=new boolean[32];
	SoftAssert soft=new SoftAssert();
	String fileName="DefectTracking_27";

	@Given("^user is in Testrepository page$")
	public void user_is_in_Testrepository_page() throws Throwable {
		try
	      {
		   bp=new BasePage();
		   rp=new ReleasePage(driver);
		   pp=new ProjectPage(driver);
		   String projectName=Property_Lib.getPropertyValue(CONFIG_PATH+CONFIG_FILE_TPE,"Normal_Project_1");
		   String releaseName=Property_Lib.getPropertyValue(CONFIG_PATH+CONFIG_FILE_TPE,"Release_1");
		   actual[0]=pp.selectProject(projectName);
		   actual[1]=pp.selectRelease(releaseName);
		   bp.waitForElement();
		   actual[2]=rp.clickOnTestRep();
		   bp.waitForElement();
		   }
	  catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	    } 

	@When("^User click on Textexecution and create testcases and execute with any status and create defect$")
	public void user_click_on_Textexecution_and_create_testcases_and_execute_with_any_status_and_create_defect() throws Throwable {
		try
		   {
	       tr=new TestRepositoryPage(driver);
	       rp=new ReleasePage(driver);
	       tp=new TestPlanningPage(driver);
	       ep=new ExecutionPage(driver);  
	       dt=new DefectTracking(driver);
		   int testcase=Excel_Lib.getNumberData(INPUT_PATH_6,"DefectTracking", 1, 26);
		   String name=UNIQUE+Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 17);
		   String desc=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 18);
		   String cycle=UNIQUE+Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 19);
		   String build=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 20);
		   String environ=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 21);
		   String hide=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 22);
		   String bulk_type_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 23);
		   String phase=UNIQUE+Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 17);
		   String releaseName=Property_Lib.getPropertyValue(CONFIG_PATH+CONFIG_FILE_TPE,"Release_1");
		   String status=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 25);
		   String syear=Integer.toString(Excel_Lib.getNumberData(INPUT_PATH_6,"TestCycle", 1, 0));
		   String smonth=Excel_Lib.getData(INPUT_PATH_6,"TestCycle", 1, 1);
		   String sdate=Integer.toString(Excel_Lib.getNumberData(INPUT_PATH_6,"TestCycle", 1, 2));
		   String eyear=Integer.toString(Excel_Lib.getNumberData(INPUT_PATH_6,"TestCycle", 1, 3));
		   String emonth=Excel_Lib.getData(INPUT_PATH_6,"TestCycle", 1, 4);
		   String edate=Integer.toString(Excel_Lib.getNumberData(INPUT_PATH_6,"TestCycle", 1, 5));  
		   String cyclename[]=new String[1];
		   cyclename[0]=cycle;
		   String phase1[]=new String[1];
		   phase1[0]=name;
		   actual[3]=tr.clickOnRelease(releaseName);
		   bp.waitForElement();
		   actual[4]=tr.addNode(name, desc);
		   bp.waitForElement();
		   actual[5]=tr.doubleClickOnRelease(releaseName);
		   bp.waitForElement();
		   actual[6]=tr.navigateToNode(releaseName,phase1);
		   bp.waitForElement();
		   actual[7]=tr.addTestCase();
		   actual[8]=tr.addTestCase();
		   bp.waitForElement();
		   actual[9]=rp.clickOnTestPlanning();
		   bp.waitForElement();
		   actual[10]=tp.clickOnAddTestCycleSymbol();
		   bp.waitForElement();
		   actual[11]=tp.CreateCycle(cycle, build, environ, hide);
		   bp.waitForElement();
		   actual[12]=tp.enterStartDatAndEndDate(syear, smonth, sdate, eyear, emonth, edate);
		   bp.waitForElement();
		   actual[13]=tp.saveTestCycle();
		   bp.waitForElement();
		   actual[14]=tp.navigateToCycle(cyclename);
		   bp.waitForElement();
		   actual[15]=tp.addPhaseToCycle(name);
		   bp.waitForElement();
		   actual[16]=tp.bulkAssignment(bulk_type_1);
		   tp.goBackToCycle();
		   
		   String[] str=new String[2];
		   str[0]=cycle;
		   str[1]=phase;
		   actual[17]=rp.clickOnTestExecution();
		   bp.waitForElement();
		   actual[18]=tr.navigateToNode(releaseName, str);
		   bp.waitForElement();
		   actual[19]=ep.selectAndDeselectAllTestCase();
		   //tr.select_all_TestCase.click();
		   bp.waitForElement();
		   actual[20]=ep.changeMultiSelectedStatus(status);
		   bp.waitForElement();	
		   actual[21]=ep.clickOnDefectButton(testcase);
		   bp.waitForElement();
		   }
		 catch(Exception e)
		   {
		   lb=new LaunchBrowser();		 
		   lb.getScreenShot(fileName);
		   e.printStackTrace();
		   driver.close();
		   Relogin_TPE rl=new Relogin_TPE();
		   rl.reLogin();			   
		   throw e;     
	    }  
	}

	@When("^User try to modify defect project in created defect$")
	public void user_try_to_modify_defect_project_in_created_defect() throws Throwable {
		try
	    {
       String project=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 0);
	   String issuetype=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 1);
	   String summary_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 29);
	   String components=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 3);
	   String descType=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 100);
	   String desc_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 28);
	   String fixVersion=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 6);
	   String priority=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 7);
	   String assignee=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 8);
	   String labels=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 9);
	   String sprint=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 10);
	   String env=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 11);
	   String epicLink=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 12);
	   String affectsVersion=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 13);
	   String linkedIssue=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 14);
	   actual[22]=dt.createDefect(project, issuetype);
	   bp.waitForElement();
	   actual[23]=dt.fileNewDefect(summary_1, components, descType, desc_1, fixVersion, priority, sprint, assignee, labels, env, epicLink, affectsVersion, linkedIssue);
	   bp.waitForElement();
	   bp.waitForElement();
	   dt.createButtonSave.click();
	   bp.waitForElement();
	   bp.waitForElement();
	   bp.waitForElement();
	   bp.waitForElement();
	   bp.waitForElement();
	   bp.waitForElement();
	   bp.waitForElement();
	    }
	    catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	} 

	@Then("^Should not be able to edit and modify the Defect$")
	public void should_not_be_able_to_edit_and_modify_the_Defect() throws Throwable {
		try
	    {
	    dt.validateDefectProject.clear();
		}
		catch(InvalidElementStateException e)
		{
	   System.out.println("Project data is not editable");
		bp.waitForElement();
		bp.waitForElement();
		dt.updateCancelButton.click();
		bp.waitForElement();
		//ep.cancelDefect.click();
		}

	  catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	} 

	@When("^User search the defect and try to modify subtask project after submission in TestExecution$")
	public void user_search_the_defect_and_try_to_modify_subtask_project_after_submission_in_TestExecution() throws Throwable {
		try
	    {
			String summary_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 29);	
		    String components=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 3);
	        String descType=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 4);
	   	    String desc_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 28);
	   	    String fixVersion=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 6);
	    	String priority=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 7);
		    String assignee=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 8);
		    String labels=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 9);
		    String sprint=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 10);
		    String env=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 11);
		    String epicLink=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 12);
		    String affectsVersion=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 13);
		    String linkedIssue=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 14);
			String issuetype_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 32);
		    String defectID=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 16);
		    String selectSearch=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 27);
		    actual[24]=ep.defectSearch(selectSearch, defectID);
		    bp.waitForElement();
		    actual[25]=ep.clickOnSubtask();
		    bp.waitForElement();
		    actual[26]=dt.createDefectSubtask(issuetype_1);
		    bp.waitForElement();
		    actual[27]=dt.fileNewDefect(summary_1, components, descType, desc_1, fixVersion, priority, assignee, labels, sprint, env, epicLink, affectsVersion, linkedIssue);
			bp.waitForElement();
			bp.waitForElement();
			   bp.waitForElement();
			   dt.createButtonSave.click();
			   bp.waitForElement();
			   bp.waitForElement();
			   bp.waitForElement();
			   bp.waitForElement();
			   bp.waitForElement();
			   bp.waitForElement();
			   bp.waitForElement();

	    }
	  catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	} 

	@Then("^Should not be able to edit and modify the subtask$")
	public void should_not_be_able_to_edit_and_modify_the_subtask() throws Throwable {
		try
	    {
	    dt.validateDefectProject.clear();
		}
		catch(InvalidElementStateException e)
		{
	    System.out.println("Project data is not editable");
	    bp.waitForElement();
		bp.waitForElement();
		dt.updateCancelButton.click();
		bp.waitForElement();
		//ep.cancelDefect.click(); 
		}
	  catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	} 

	@When("^User search the defect and try to modify subtask parentsID after submission in TestExecution$")
	public void user_search_the_defect_and_try_to_modify_subtask_parentsID_after_submission_in_TestExecution() throws Throwable {
		try
	    {
				String summary_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 29);	
			    String components=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 3);
		        String descType=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 4);
		   	    String desc_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 28);
		   	    String fixVersion=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 6);
		    	String priority=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 7);
			    String assignee=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 8);
			    String labels=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 9);
			    String sprint=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 10);
			    String env=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 11);
			    String epicLink=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 12);
			    String affectsVersion=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 13);
			    String linkedIssue=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 100, 14);
				String issuetype_1=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 32);
			    String defectID=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 16);
			    String selectSearch=Excel_Lib.getData(INPUT_PATH_6,"DefectTracking", 1, 27);
			    actual[28]=ep.defectSearch(selectSearch, defectID);
			    bp.waitForElement();
			    actual[29]=ep.clickOnSubtask();
			    bp.waitForElement();
			    actual[30]=dt.createDefectSubtask(issuetype_1);
			    bp.waitForElement();
			    actual[31]=dt.fileNewDefect(summary_1, components, descType, desc_1, fixVersion, priority, assignee, labels, sprint, env, epicLink, affectsVersion, linkedIssue);
				bp.waitForElement();
				bp.waitForElement();
				   bp.waitForElement();
				   dt.createButtonSave.click();
				   bp.waitForElement();
				   bp.waitForElement();
				   bp.waitForElement();
				   bp.waitForElement();
				   bp.waitForElement();
				   bp.waitForElement();
				   bp.waitForElement();
	             }
	  catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	
	}
	@Then("^Should not be able to modify the subtask$")
	public void should_not_be_able_to_modify_the_subtask() throws Throwable {
		try
	    {
	    dt.validateSubtaskParentId.clear();
	    for(int k=0;k<=actual.length-1;k++)
		{
			System.out.println("Actual["+k+"]="+actual[k]);
			soft.assertEquals(actual[k], true);
		}
		soft.assertAll();
		}
		catch(InvalidElementStateException e)
		{
	    System.out.println("Project data is not editable");
	    bp.waitForElement();
		bp.waitForElement();
		dt.updateCancelButton.click();
		bp.waitForElement();
		ep.cancelDefect.click();
		}
	  catch(Exception e)
	    {
	    lb=new LaunchBrowser();	 
	    lb.getScreenShot(fileName);
		e.printStackTrace();
		driver.close();
	    Relogin_TPE rl=new Relogin_TPE();
	    rl.reLogin();
	    throw e; 
	    }
	} 
}

