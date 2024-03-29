<?php

abstract class Controller {

    protected $title = "";
    protected $view;
    protected $data = array();
    protected $parameters = array();
    protected $nav = false;
    protected $footer = false;

    public function __construct(Array $url = array()) {
        $this->parameters = $url;
        $this->main();
    }

    public abstract function main();

    public function run() {
        extract($this->data);
        if (strlen($this->view))
            require("Views/" . $this->view . ".php");
        else {
            if(isset($this->data["form"]))
                require("Views/basic-form.php");
            else
            require ("Views/emptyview.php");
        }
    }

    public function redirect($url) {
        header("Location:" . $url);
        exit();
    }

    protected function addMessage(Message $message) {
        Message::add($message);
    }

    public function hasRights() {
        if (!($this->isLogged())) {
            $this->addMessage(new Message("Nedostatečná práva!"));
            $this->redirect("/Login");
        }
    }

    public function forceLogged() {
        if (!$this->isLogged()) {
            $this->addMessage(new Message("Nedostatečná práva!"));
            $this->redirect("/Login");
        }
    }

    public function isLogged() {
        if (!empty($_SESSION["user"]))
            return true;

        return false;
    }

    public function footer() {
        return $this->footer;
    }

    public function nav() {
        return $this->nav;
    }
    public function getTitle(){
        return (string)"".$this->title;
    }
}
