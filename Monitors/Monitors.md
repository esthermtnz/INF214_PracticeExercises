## Sample task: Monitors
A savings account is shared by several people (processes). Each person may deposit or withdraw funds from the account. The current balance in the account is the sum of all deposits to date minus the sum of all withdrawals to date. The balance must never become negative. A deposit never has to delay (except for mutual exclusion), but a withdrawal has to wait until there are suficient funds. A junior software developer was asked to implement a monitor to solve this problem, using Signal-and-Continue discipline. Here is the code the junior developer has written so far

<details>
<summary> Correct solution: </summary>

    ```

        monitor Account {
            int balance = 0;

            cond cv;

            procedure deposit(int amount) 
            {
                balance = balance + amount;
                signal_all(cv);
            }

            procedure withdraw(int amount) 
            {   
                while(balance < amount) wait(cv);
                balance = balance - amount;
            }

        }

    ```

</details>