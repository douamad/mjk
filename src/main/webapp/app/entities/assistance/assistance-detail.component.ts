import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssistance } from 'app/shared/model/assistance.model';

@Component({
  selector: 'jhi-assistance-detail',
  templateUrl: './assistance-detail.component.html',
})
export class AssistanceDetailComponent implements OnInit {
  assistance: IAssistance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assistance }) => (this.assistance = assistance));
  }

  previousState(): void {
    window.history.back();
  }
}
