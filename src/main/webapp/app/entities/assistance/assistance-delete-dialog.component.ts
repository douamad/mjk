import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssistance } from 'app/shared/model/assistance.model';
import { AssistanceService } from './assistance.service';

@Component({
  templateUrl: './assistance-delete-dialog.component.html',
})
export class AssistanceDeleteDialogComponent {
  assistance?: IAssistance;

  constructor(
    protected assistanceService: AssistanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.assistanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('assistanceListModification');
      this.activeModal.close();
    });
  }
}
