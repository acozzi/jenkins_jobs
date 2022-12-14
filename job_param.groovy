job('DSL Job Parametrizado'){
  description('Job dsl de ejemplo para el curso dsl')
  scm {
        git('https://github.com/macloujulian/jenkins.job.parametrizado', 'main') { node -> 
            node / gitConfigName('acozzi')
            node / gitConfigEmail('acozzzi@gmail.com')
        }
    }
  parameters {
  	stringParam('nombre', defaultValue = 'Alejandro', description = 'Paramewtro de string para un job booleano')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
  	mailer('acozzzi@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
