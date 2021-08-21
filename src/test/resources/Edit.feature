Feature: TargetSourceTable Edit and Delete

  @EditAndDeleteTarget
  Scenario: Add items to the ToDoApp
    Given I create Targets with the following data
      | Target | SchemaOwner |
      | T1     | dbo1        |
      | T2     | dbo2        |
    Given I create Sources with the following data
      | Target | Source | SystemTypeID | SchemaOwner | Active |
      | T1     | S1     | SAP          | sq1         |        |
      | T1     | S2     | SAP          | sq2         |        |
      | T2     | S3     | SAP          | sq1         |        |
      | T2     | S4     | SAP          | sq2         |        |
    Given I create Tables the following data
      | Target | Source | Table | PackageType | Priority | Active | DeleteTarget |
      | T1     | S1     | TB10  | CranPort    |       10 |        | Yes          |
      | T1     | S1     | TB11  | CranPort    |       15 |        | Yes          |
      | T2     | S2     | TB20  | CranPort    |       20 |        | Yes          |
      | T2     | S2     | TB21  | CranPort    |       25 |        | Yes          |
      | T3     | S3     | TB30  | CranPort    |       30 |        | Yes          |
      | T3     | S3     | TB31  | CranPort    |       35 |        | Yes          |
      | T4     | S4     | TB40  | CranPort    |       40 |        | Yes          |
      | T4     | S4     | TB41  | CranPort    |       45 |        | Yes          |

  Scenario: Edit the Target, BuildPackage and Refresh on Table
    Given I am on Target page
    When I edit the details of 'T1' Target
      | Target | SchemaOwner |
      | T1_New | dbo1_new    |
    Then I see 'T1_New' Target on the page
    When I select 'T1_New' Target on the page
    And I navigate to Tables page of 'S2' Source
    And I select 'TB21' Table
    Then I see Build Package icon 'enabled'
    And I see Refresh icon 'disabled'
    When I perform Build Package
    And I see Refresh icon 'enabled'
    When I perform Refresh
    Then I see Refresh is successful

  Scenario: Edit the Source, BuildPackage and Refresh on Table.
    Given I am on Target page
    When I select 'T2' Target on the page
    When I edit "S4" Source with the following data
      | Source | SystemTypeID | SchemaOwner | Active |
      | S4_New | SAP          | sq1_new     |        |
    Then I see 'S4' Source on the page
    When I perform Test Connection on 'T2' target
    Then I see Test Connection is successful
    When I navigate to Tables page of 'S4' Source
    And I perform Build Package on 'TB41' Table    
    And I see Refresh icon 'enabled'
    When I perform Refresh
    Then I see Refresh is successful

  Scenario: Edit the Table, Test Connection on Target
    Given I am on Target page
    When I select 'T2' Target on the page
    When I perform Test Connection on 'T2' target
    Then I see Test Connection is successful
    When I navigate to Tables page of 'S3' Source
    When I select 'TB30' Table
    When I perform Build Package and Refresh
    When I navigate back to Target page

  Scenario: Delete Targets
    Given I am on Target page
    When I delete 'T1' Target
    Then I see 'T1' Target is not present
    And I delete 'T2' Target
    Then I see 'T2' Target is not present
